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
              google-java-format
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
