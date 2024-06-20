export function addEventListenerForThemes(callback) {
  document.querySelector('.js-blue').addEventListener('click', () => {
    callback('rgb(99, 177, 211)');
  });
  document.querySelector('.js-red').addEventListener('click', () => {
    callback('rgb(211, 99, 99)');
  });
  document.querySelector('.js-green').addEventListener('click', () => {
    callback('rgb(99, 211, 130)');
  });
  document.querySelector('.js-yellow').addEventListener('click', () => {
    callback('rgb(233, 223, 129)');
  });
}
