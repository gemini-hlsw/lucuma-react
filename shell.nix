# save this as shell.nix
{ pkgs ? import <nixpkgs> {}}:

pkgs.mkShell {
  nativeBuildInputs = [ 
     pkgs.jdk
     pkgs.nodejs-14_x
   ];
}

