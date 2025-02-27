<%@ page import="java.util.List" %>
<%@ page import="org.megacity.cabservice.dto.driver_dto.DriverDetailDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="CSS/manage_dashboard.css">
</head>

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