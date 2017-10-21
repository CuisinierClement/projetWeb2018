/* jshint bitwise:false, node:true */
/* tslint:disable:no-bitwise no-var-keyword typedef */

export function generateColor(name: string)  {
  // Input : nom complet de l'utilisateur (chaine de charactere)
  // Output: code couleur HTML (eg #00000 pour le noir)
  let hash = 0;
  var i, color;
  for (i = 0; i < name.length; i++) {
    // pour chaque caractere, on recupere l'unicode
    // a << b: bitwise operation left shift
    //         comparaison bit à bit avec un decalage a gauche
    hash = name.charCodeAt(i) + ((hash << 6) - hash);
  }
  // a | b: bitwise operation AND
  // 0x00FFFFFF : Cyan
  color = (hash & 0x00FFFFFF).toString(16).toUpperCase();

  return '#' + '00000'.substring(0, 6 - color.length) + color;
};
