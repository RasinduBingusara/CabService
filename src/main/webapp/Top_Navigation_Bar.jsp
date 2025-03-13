<%@ page import="org.megacity.cabservice.model.User" %>
<%
  User navigatorUser = (User) session.getAttribute("user");
  System.out.println("Loaded");
  if(navigatorUser != null && navigatorUser.getUserType()!=null){
    if(navigatorUser.getUserType().equals("Admin")){
      response.sendRedirect("admin_dashboard");
    } else if (navigatorUser.getUserType().equals("Driver")) {
      System.out.println("Driver");
      request.getRequestDispatcher("driver_manage_booking.jsp").forward(request, response);
    }
  }
%>

<header>
  <nav>
    <div class="logo">Mega City Cab</div>

    <ul class="top-nav-links">
      <li><a href="home.jsp">Home</a></li>
      <li><a href="service.jsp">Services</a></li>
      <li><a href="about-us.jsp">About Us</a></li>
      <li><a href="user_guide.jsp">Guide</a></li>
      <li><a href="customer_add_booking.jsp">Book Now</a></li>
      <li><a href="signup?type=Driver">Join as a Driver</a></li>

      <%
        if(navigatorUser == null){
      %>
      <li><a href="login.jsp">Sign In</a></li>
      <li><a href="signup?type=Customer">Sign Up</a></li>
      <%
      } else {
      %>
      <li class="profile-menu">
        <a href="#"> <%= navigatorUser.getFirstName() %></a>
        <ul class="profile-dropdown">
          <form action="profile" method="get">
            <input type="hidden" name="action" value="c">
            <input type="submit" value="Profile">
          </form>
          <form action="profile" method="post">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="logout">
          </form>
        </ul>
      </li>
      <%
        }
      %>
    </ul>
  </nav>
</header>

<style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }

  header {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    background: #ffcc00;
    color: #333;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    z-index: 1000;
  }

  nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 12px 20px;
  }

  .logo {
    font-size: 24px;
    font-weight: bold;
    color: #333;
  }

  .top-nav-links {
    list-style: none;
    display: flex;
    align-items: center;
    gap: 15px;
    padding: 0;
    margin: 0;
  }

  .top-nav-links li {
    position: relative;
  }

  .top-nav-links a {
    text-decoration: none;
    color: #333;
    padding: 10px 15px;
    border-radius: 5px;
    transition: background 0.3s ease, color 0.3s;
    display: inline-block;
  }

  .top-nav-links a:hover {
    background: #333;
    color: #ffcc00;
  }

  .profile-menu {
    position: relative;
  }

  .profile-menu > a {
    display: flex;
    align-items: center;
    padding: 10px 15px;
    border-radius: 5px;
    background-color: #333;
    color: #ffcc00;
    cursor: pointer;
  }

  .profile-menu a:hover {
    background-color: #555;
  }

  .profile-menu:hover .profile-dropdown {
    display: block;
  }

  .profile-dropdown {
    display: none;
    position: absolute;
    background-color: white;
    min-width: 160px;
    top: 40px;
    right: 0;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    overflow: hidden;
  }

  .profile-menu:hover .profile-dropdown {
    display: block;
    font-family: Arial, sans-serif;
  }

  .profile-dropdown li {
    list-style: none;
  }

  .profile-dropdown a,
  .profile-dropdown form {
    display: block;
    text-align: left;
    padding: 10px;
    color: #333;
    text-decoration: none;
    width: 100%;
  }

  .profile-dropdown a:hover {
    background-color: #ffcc00;
    color: #333;
  }

  .profile-dropdown form {
    margin: 0;
    padding: 0;
  }

  .profile-dropdown form input[type="submit"] {
    background: none;
    border: none;
    color: #333;
    width: 100%;
    text-align: left;
    padding: 10px;
    cursor: pointer;
    font-size: 14px;
  }

  .profile-dropdown form input[type="submit"]:hover {
    background-color: #ffcc00;
    color: #333;
  }

  @media (max-width: 768px) {
    nav {
      flex-direction: column;
      align-items: center;
    }

    .top-nav-links {
      flex-direction: column;
      width: 100%;
      padding: 10px 0;
    }

    .top-nav-links li {
      width: 100%;
      text-align: center;
    }

    .top-nav-links a {
      width: 100%;
      display: block;
    }

    .profile-menu {
      width: 100%;
    }

    .profile-menu > a {
      display: flex;
      justify-content: center;
    }

    .profile-dropdown {
      right: auto;
      left: 0;
      width: 100%;
      text-align: center;
    }
  }
</style>

