
let map;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: -32.8933144, lng: -68.8490985},
    zoom: 8,
  });
  let marcadorUsuario= new google.maps.Marker({
  position:obj,
    title:'Tu Ubicacion'
  });
}

/*
let infoWindow;
infoWindow = new google.maps.InfoWindow();
const locationButton = document.createElement("button");                                     
locationButton.textContent = "Ubicacion actual";
locationButton.classList.add("custom-map-control-button");
map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
locationButton.addEventListener("click", () => {
  // Try HTML5 geolocation.
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
        (position) => {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          infoWindow.setPosition(pos);
          infoWindow.setContent("Lugar Encontrado.");
          infoWindow.open(map);
          map.setCenter(pos);
        },
        () => {
          handleLocationError(true, infoWindow, map.getCenter());
        }
      );
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infoWindow, map.getCenter());
    }
  });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation."
  );
  infoWindow.open(map);
}
/*if (navigator.geolocation){
  navigator.geolocation.getCurrentPosition(usuarioUbicacion =>{
    let ubicacion = {
      lat:usuarioUbicacion.coords.latitude,
      long:usuarioUbicacion.coords.longitude
    }
    dibujarmapa(ubicacion)
  })
}

const dibujarmapa = (obj) =>{
  let mapa = new google.maps.Map(document.getElementById('map'),{
    center: { lat: -32.8933144, lng: -68.8490985},
    zoom: 8,
  })
  let marcadorUsuario= new google.maps.Marker({
    position:obj,
    title:'Tu Ubicacion'
  })
  marcadorUsuario.setMap(mapa)
  

}*/

  