/**
 * 
 */

document.querySelector("form").addEventListener("submit", function(event) {
    event.preventDefault(); // フォームのデフォルト送信を防ぐ

    const formData = new FormData(this);
    const queryString = new URLSearchParams(formData).toString();

    fetch(`http://localhost:7777/shop/doLogin?${queryString}`, {
        method: "GET"
    })
    .then(response => response.text) // 必要に応じてJSONとして処理
    .then(data => console.log(data))
    .catch(error => console.error("Error:", error));
});

