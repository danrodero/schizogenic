set shell := ["bash", "-euo", "pipefail", "-c"]

default:
    @just --list

doctor:
    @java --version
    @gradle --version | sed -n '1,8p'
    @node --version
    @pnpm --version
    @postgres --version
    @gh --version | sed -n '1p'
    @nixfmt --version

format-nix:
    nixfmt flake.nix

check:
    nix flake check "path:$SCHIZOGENIC_ROOT"
    just lint-docs
    cd apps/backend/ && ./gradlew test

lint-docs:
    @git ls-files --cached --others --exclude-standard -z -- "*.md" | xargs -0 -r markdownlint-cli2

db-init:
    @mkdir -p "$PGDATA" "$PGHOST"
    @if [ -f "$PGDATA/PG_VERSION" ]; then \
        echo "PostgreSQL cluster already initialized at $PGDATA"; \
    else \
        initdb --pgdata="$PGDATA" --username="$PGUSER" --auth-local=trust --auth-host=trust; \
    fi

db-start: db-init
    @mkdir -p "$PGHOST"
    @if pg_ctl --pgdata="$PGDATA" status >/dev/null 2>&1; then \
        echo "PostgreSQL is already running"; \
    else \
        pg_ctl --pgdata="$PGDATA" --log="$PGDATA/server.log" --options="-k $PGHOST -p $PGPORT" start; \
    fi

db-create: db-start
    @if psql --dbname=postgres --tuples-only --no-align --command="SELECT 1 FROM pg_database WHERE datname = '$PGDATABASE'" | grep -qx 1; then \
        echo "Database $PGDATABASE already exists"; \
    else \
        createdb "$PGDATABASE"; \
    fi

db-status:
    @pg_ctl --pgdata="$PGDATA" status

db-stop:
    @if [ ! -f "$PGDATA/PG_VERSION" ]; then \
        echo "PostgreSQL cluster has not been initialized"; \
    elif pg_ctl --pgdata="$PGDATA" status >/dev/null 2>&1; then \
        pg_ctl --pgdata="$PGDATA" stop; \
    else \
        echo "PostgreSQL is not running"; \
    fi
