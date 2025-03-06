
<div id="popup" class="popup">
    <div class="popup-content">
        <span class="close-btn" onclick="closePopup()">&times;</span>
        <h2>Information</h2>
        <p class="sub-title">ID: <p id="uid"></p></p>
        <p class="sub-title">First Name: <p id="first_name"></p></p>
        <p class="sub-title">Last Name: <p id="last_name"></p></p>
        <p class="sub-title">Email: <p id="email"></p></p>
        <p class="sub-title">Contact Number: <p id="contact_number"></p></p>
        <p class="sub-title">Status: <p id="status"></p></p>
        <p class="sub-title">License No: <p id="driver_license"></p></p>
        <p class="sub-title">NIC: <p id="nic"></p></p>
        <p class="sub-title">Address: <p id="address"></p></p>
        <p class="sub-title">Updated At: <p id="updated_at"></p></p>
        <p class="sub-title">Created At: <p id="created_at"></p></p>
    </div>
</div>

<script>

    function openPopup(id){
        fetch("drivers?action=single&id="+id)
            .then(response => response.json())
            .then(data => {
                document.getElementById("uid").textContent = data.id;
                document.getElementById("first_name").textContent = data.first_name;
                document.getElementById("last_name").textContent = data.last_name;
                document.getElementById("email").textContent = data.email;
                document.getElementById("contact_number").textContent = data.contact_number;
                document.getElementById("status").textContent = data.status;
                document.getElementById("driver_license").textContent = data.driver_license;
                document.getElementById("nic").textContent = data.nic;
                document.getElementById("address").textContent = data.address;
                document.getElementById("updated_at").textContent = data.updated_at;
                document.getElementById("created_at").textContent = data.created_at;
            })
            .catch(error => console.error("Error fetching driver:", error));
        document.getElementById("popup").style.display = "block";
    }

    function closePopup() {
        document.getElementById("popup").style.display = "none";
    }
    window.onload = closePopup();


    window.onclick = function(event) {
        if (event.target === document.getElementById("popup")) {
            closePopup();
        }
    };
</script>

<style>

    .popup {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.09);
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .popup-content {
        background: white;
        top: 50%;
        left: 50%;
        padding: 20px;
        width: 400px;
        max-width: 90%;
        border-radius: 10px;
        text-align: center;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        position: relative;
    }

    .sub-title{
        font-weight: bold;
    }

    .close-btn {
        position: absolute;
        top: 10px;
        right: 15px;
        font-size: 20px;
        cursor: pointer;
    }

    .close-btn:hover {
        color: red;
    }
</style>
