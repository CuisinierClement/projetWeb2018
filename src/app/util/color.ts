/* jshint bitwise:false, node:true */
/* tslint:disable:no-bitwise no-var-keyword typedef */

export function generateColor(name: string)  {
  let hash = 0;
  var i, color;
  for (i = 0; i < name.length; i++) {
    hash = name.charCodeAt(i) + ((hash << 4) - hash);
  }

  color = (hash & 0x00FFFFFF)
    .toString(16)
    .toUpperCase();

  return '#' + '00000'.substring(0, 6 - color.length) + color;
};
