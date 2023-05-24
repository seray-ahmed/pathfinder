window.addEventListener("load", async () => {
    await changeWeatherBoxInIndexPage();
})
async function changeWeatherBoxInIndexPage(){
    const weatherBox = document.getElementsByClassName("top-box top-box-a")[0];
    await navigator.geolocation.getCurrentPosition(async data => {
        const {latitude, longitude} = data.coords;
        const result = await getWeatherInfoByTownName(await getTownNameByCoordinates(latitude, longitude))
        console.log(result);
        const temp = result.current.temp_c;
        const feelsLike = result.current.feelslike_c;
        const townName = result.location.name;
        const country = result.location.country;
        const imgPath = "https:" + result.current.condition.icon;
        const lastUpdated = result.current.last_updated;
        const weatherBoxTitle1 = weatherBox.querySelector("h3");
        const weatherBoxTitle2 = weatherBox.querySelector("h4");
        const weatherBoxTitle3 = weatherBox.querySelector("h5");
        weatherBoxTitle1.textContent = `${townName}, ${country}`;
        weatherBoxTitle2.textContent = `Temperature: ${temp} °C`;
        weatherBoxTitle3.textContent = `Feels like: ${feelsLike} °C`;
        weatherBox.querySelector("img").src = imgPath;
        weatherBox.querySelector("p").textContent = `last updated at: ${lastUpdated.split(" ")[1]}`;
    })
}
async function getWeatherInfoByTownName(townName) {
    const response = await fetch(`https://api.weatherapi.com/v1/current.json?key=ebddf8c7e18f4ecfbdd93841231005&q=${townName}&aqi=yes`);
    return await response.json();
}

async function getTownNameByCoordinates(latitude, longitude){
    const URL = `https://nominatim.openstreetmap.org/reverse?format=json&lat=${latitude}&lon=${longitude}`;
    const response = await fetch(URL);
    const responseJSON = await response.json();
    const townName = responseJSON.address.city;
    return townName;
}