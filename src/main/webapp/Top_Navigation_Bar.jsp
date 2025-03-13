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
      <li><a href="#services">Services</a></li>
      <li><a href="#about">About Us</a></li>
      <li><a href="#contact">Contact</a></li>
      <li><a href="#book">Book Now</a></li>
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
          <li><a href="profile?action=c">Profile</a></li>
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
    box-sizing: border-box;
  }

  header {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    background: #ffcc00;
    color: #333;
    text-align: center;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    z-index: 1000;
    align-content: center;
  }

  nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    max-width: 1200px;
    margin: auto;
    flex-direction: row;
  }

  .logo {
    font-size: 28px;
    font-weight: bold;
    color: #333;
  }

  .top-nav-links {
    list-style: none;
    display: flex;
    gap: 20px;
    padding: 0;
  }

  .top-nav-links li {
    display: inline-block;
    position: relative;
  }

  .top-nav-links a {
    color: #333;
    text-decoration: none;
    font-size: 18px;
    padding: 8px 15px;
    border-radius: 5px;
    transition: background 0.3s, color 0.3s;
  }

  .top-nav-links a:hover {
    background: #333;
    color: #ffcc00;
  }

  .profile-menu {
    position: relative;
  }

  .profile-menu a {
    display: flex;
    align-items: center;
    gap: 5px;
  }

  .profile-dropdown {
    display: none;
    position: absolute;
    background: white;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    top: 100%;
    left: 0;
    min-width: 150px;
    padding: 5px 0;
  }

  .profile-dropdown li {
    display: block;
  }

  .profile-dropdown a {
    display: block;
    padding: 10px;
    color: #333;
    text-align: left;
  }

  .profile-dropdown a:hover {
    background: #ffcc00;
    color: #333;
  }

  .profile-menu:hover .profile-dropdown {
    display: block;
  }
</style>
