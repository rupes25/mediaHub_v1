import "../css/componentsCSS/Navbar.css";
import { useState } from "react";
import Profile from "./Profile";
import { useEffect } from "react";
import api from "../api/axios";

const Navbar = () => {

    const [showProfile, setShowProfile] = useState(false)

        const [user, setUser] = useState(null)


    // homepage reload hone ke saath user info ko leke aana hai taaki user profile update kiya jaa sake.

    // authenticationMiddleware me user detail already aarhi hai hm ussi se user info nikal skte hai bina koi naya end point banaye.
    useEffect(()=>{
      const fetchUserDetails = async ()=>{
       try{
         const response = await api.get('/check-auth')
        setUser(response.data.user);
       }
       catch(err){
        console.log(err.message);
        alert("Unable to get user info.");
       }

      }
      fetchUserDetails();

    },[])

  return (
    <nav className="navbar">
      {/* Logo */}
      <a href="/" className="navbarLogo">
        Media<span>HUB</span>
      </a>

      {/* Navigation */}
      <div className="navbarLinks">
        <a href="/dashboard" className="active">
          Home
        </a>

        <a href="/projects">
          Projects
        </a>

        <a href="/explore">
          Explore
        </a>
      </div>

      {/* User section */}
      <div className="navbarUser" onClick={()=>setShowProfile(!showProfile)}>
        <div className="userInfo">
          <div className="profileImage">
            <span>{user?.fullName?.[0]?.toUpperCase() ?? "?"}</span>
          </div>

          <div className="userDetails">
            <p className="userName">
              {user?.fullName ?? "Loading..."}
            </p>

            <span className="userRole">
              Creator
            </span>
          </div>
        </div>

        <button
          className="profileButton"
          aria-label="Open profile menu"
        >
          ↓
        </button>
      </div>

      {showProfile && user && <Profile props = {user}/>} 
    </nav>
  );
};

export default Navbar;