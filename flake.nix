{
  description = "Schizogenic production-learning development environment";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
  };

  outputs =
    { nixpkgs, ... }:
    let
      supportedSystems = [
        "x86_64-linux"
        "aarch64-linux"
        "aarch64-darwin"
      ];
      forAllSystems = nixpkgs.lib.genAttrs supportedSystems;
    in
    {
      formatter = forAllSystems (system: nixpkgs.legacyPackages.${system}.nixfmt);

      devShells = forAllSystems (
        system:
        let
          pkgs = nixpkgs.legacyPackages.${system};
          jdk = pkgs.jdk25;
        in
        {
          default = pkgs.mkShell {
            packages = with pkgs; [
              jdk
              gradle_9
              jdt-language-server
              nodejs_24
              pnpm
              typescript-language-server
              postgresql_18
              git
              gh
              jq
              just
              markdownlint-cli2
              nil
              nixfmt
              shellcheck
            ];

            JAVA_HOME = "${jdk}";

            shellHook = ''
              export SCHIZOGENIC_ROOT="$(git rev-parse --show-toplevel 2>/dev/null || pwd)"

              # Human development shells use Dan's existing GitHub account and
              # SSH transport. Agent processes run as harness and retain the
              # injected Clawstopher credentials used for review and fast-track
              # maintenance PRs.
              if [ "$(id -un)" = "louie" ]; then
                unset GH_TOKEN GITHUB_TOKEN
                export GH_CONFIG_DIR="$HOME/.config/gh-danrodero"
                git -C "$SCHIZOGENIC_ROOT" config --local user.name "Dan Rodero"
                git -C "$SCHIZOGENIC_ROOT" config --local user.email "danrodero@outlook.com"
                git -C "$SCHIZOGENIC_ROOT" remote set-url --push origin \
                  git@github.com:danrodero/schizogenic.git
              fi

              export PGDATA="$SCHIZOGENIC_ROOT/.local/state/postgres/data"
              export PGHOST="$SCHIZOGENIC_ROOT/.local/state/postgres/socket"
              export PGDATABASE="schizogenic_dev"
              export PGUSER="schizogenic"
              export PGPORT="5432"
            '';
          };
        }
      );
    };
}
