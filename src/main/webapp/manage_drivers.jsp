<%@ page import="java.util.List" %>
<%@ page import="org.megacity.cabservice.dto.driver_dto.DriverDetailDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  List<DriverDetailDTO> drivers = null;
%>
<html>
<head>
  <title>Title</title>
</head>
<style>

  /* Heading */
  h2 {
    color: #333;
    font-size: 28px;
    margin-bottom: 20px;
  }

  .add-driver-btn {
    background-color: #ffcc00;
    color: #333;
    padding: 10px 15px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    transition: 0.3s;
  }

  .add-driver-btn:hover {
    background-color: #333;
    color: #ffcc00;
  }

  /* Search Bar */
  #searchDriver {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-bottom: 10px;
    margin-top: 20px;
  }
  select {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #fff;
    color: #333;
    cursor: pointer;
    transition: 0.3s;
    appearance: none; /* Removes default arrow */
    background-image: url('data:image/svg+xml;utf8,<svg fill="%23333" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5H7z"/></svg>');
    background-repeat: no-repeat;
    background-position: right 10px center;
    background-size: 16px;
  }

  /* On Hover */
  select:hover {
    border-color: #ffcc00;
  }

  /* On Focus */
  select:focus {
    border-color: #ffcc00;
    outline: none;
    box-shadow: 0 0 5px rgba(255, 204, 0, 0.7);
  }

  /* Option Styling */
  select option {
    padding: 10px;
    font-size: 16px;
    background: white;
    color: #333;
  }

  /* Disabled Option */
  select:disabled {
    background: #f8f9fa;
    color: #999;
    cursor: not-allowed;
  }



  /* Table */
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
    font-size: 12px;
  }

  th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }

  th {
    background: #ffcc00;
    color: #333;
    font-weight: bold;
  }

  /* Buttons */
  button {
    padding: 6px 12px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
    margin-right: 5px;
  }

  .edit-btn {
    background: #28a745;
    color: white;
  }

  button:hover {
    opacity: 0.8;
  }

  /* Hover Effect */
  tr:hover {
    background: #f1f1f1;
  }

  /* Responsive Design */
  @media (max-width: 768px) {
    th, td {
      padding: 8px;
    }

    button {
      padding: 5px 10px;
      font-size: 12px;
    }

    select {
      font-size: 14px;
      padding: 8px;
    }
  }
</style>

<body>
<%@include file="dashboard_navigator.jsp"%>
<div class="content">
  <h2>Driver Management</h2>

  <a href="drivers?action=add">
    <button class="add-driver-btn">+ Add Driver</button>
  </a>

    <input type="text" id="searchDriver" placeholder="Search by Name, NIC or License..." onkeyup="searchTable()">
    <select id="statusFilter" name="statusFilter" onchange="onChangeStatus()">
        <option value="">All</option>
        <option value="Active">Active</option>
        <option value="Inactive">Inactive</option>
    </select>

  <table id="driverTable">
    <thead>
    <tr>
      <th>Driver Name</th>
      <th>Email</th>
      <th>License No.</th>
      <th>NIC No.</th>
      <th>Phone Number</th>
      <th>Address</th>
      <th>Employment Type</th>
      <th>Status</th>
      <th>Updated At</th>
      <th>Created At</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody id="driverContainer">
    </tbody>
  </table>
  <button id="loadMore" onclick="loadMoreTable()">Load More...</button>

</div>

<script>

  let count = 0;
  let container = document.getElementById("driverContainer");
  let status;
  let offset = 10;

  function onChangeStatus(){
    let keyword = document.getElementById("searchDriver").value;
    if(keyword===""){
      fetchUsers();
    }
    else{
      searchTable();
    }
  }
  function fetchUsers() {
    offset = 10;
    count = 0;
    status = document.getElementById("statusFilter").value;
      fetch("drivers?action=portion&limit=10&offset=0&status="+status)
            .then(response => response.json())
            .then(users => {
              count = users.length;
              container.innerHTML = "";
              loadTable(users);

              if(count===10){
                document.getElementById("loadMore").style.display = "block";
              }else
                document.getElementById("loadMore").style.display = "none";

            })
            .catch(error => console.error("Error fetching users:", error));
  }

  window.onload = fetchUsers;

  function loadMoreTable(){
    status = document.getElementById("statusFilter").value;
      fetch("drivers?action=portion&limit=10&offset="+offset+"&status=" + status)
          .then(response => response.json())
          .then(data => {
              count = data.length
              loadTable(data);

            if(count===10){
              document.getElementById("loadMore").style.display = "block";
            }else
              document.getElementById("loadMore").style.display = "none";
            offset += 10

          }).catch(error => console.error("Error fetching users:", error));
  }

  function searchTable(){
    status = document.getElementById("statusFilter").value;
      let keyword = document.getElementById("searchDriver").value;
      if(keyword === ""){
        fetchUsers();
      }
      else{
        fetch("drivers?action=search&keyword="+keyword+"&status="+status)
                .then(response => response.json())
                .then(data => {
                  container.innerHTML = "";
                  loadTable(data);
                })
      }

  }

  function loadTable(data){
    console.log("Status: " + status);
      data.forEach(u => {
          let newTray = document.createElement("tr");

          let name = document.createElement("td");
          name.textContent = u.first_name + " " + u.last_name;

          let email = document.createElement("td");
          email.textContent = u.email;

          let driver_license = document.createElement("td");
          driver_license.textContent = u.driver_license;

          let nic = document.createElement("td");
          nic.textContent = u.nic;

          let contactNo = document.createElement("td");
          contactNo.textContent = u.contact_number;

          let address = document.createElement("td");
          address.textContent = u.address;

          let employment_type = document.createElement("td");
          employment_type.textContent = u.employment_type;

          let status = document.createElement("td");
          status.textContent = u.status;

          let updated_at = document.createElement("td");
          updated_at.textContent = u.updated_at!=""? u.updated_at:"Not Updated Yet";

          let created_at = document.createElement("td");
          created_at.textContent = u.created_at;

          let actionTd = document.createElement("td");

          let form = document.createElement("form");
          form.setAttribute("action", "drivers?action=edit");

          let hiddenInput = document.createElement("input");
          hiddenInput.setAttribute("type", "hidden");
          hiddenInput.setAttribute("name", "driverEmail");
          hiddenInput.setAttribute("value", u.email);

          let button = document.createElement("button");
          button.classList.add("edit-btn");
          button.setAttribute("type","submit");
          button.textContent = "Edit";

          form.appendChild(hiddenInput);
          form.appendChild(button);
          actionTd.appendChild(form);

          newTray.appendChild(name);
          newTray.appendChild(email);
          newTray.appendChild(driver_license);
          newTray.appendChild(nic);
          newTray.appendChild(contactNo);
          newTray.appendChild(address);
          newTray.appendChild(employment_type);
          newTray.appendChild(status);
          newTray.appendChild(updated_at);
          newTray.appendChild(created_at);
          newTray.appendChild(actionTd);

          container.appendChild(newTray);
      })

  }

</script>
</body>


</html>