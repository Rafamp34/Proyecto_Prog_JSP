function showContent(contentId) {
  console.log("showContent called with contentId: " + contentId); 
  document.getElementById('home').classList.add('hidden');
  document.getElementById('perfil').classList.add('hidden');
  
  document.getElementById(contentId).classList.remove('hidden');
}

console.log("Script loaded"); 

  window.addEventListener("pageshow" , function (event) {
    if (event.persisted) {
    window.location.reload();
    }
    });
    

