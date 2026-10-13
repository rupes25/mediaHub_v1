import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import "../css/componentsCSS/Profile.css";


const Profile = ({props}) => {

  const navigate = useNavigate();

  const handleClick = async()=>{
    try{
      await api.post("/logout");
      alert("Logout successful.");
      navigate("/login")


    }
    catch(err){
      console.log(err.message);
      alert("unable to perform this action.")
    }
  }


  return (
    <div className="profilePopup">

      {/* Profile header */}
      <div className="profileHeader">
        <div className="profileAvatar">
          <span>{props.fullName[0].toUpperCase()}</span>
        </div>

        <div className="profileIdentity">
          <h3>{props.fullName}</h3>
          <span>@{props.username}</span>
        </div>

        <div className="onlineDot"></div>
      </div>

      {/* User information */}
      <div className="profileInfo">

        <div className="infoItem">
          <span className="infoLabel">
            User's email
          </span>

          <p>
           {props.email}
          </p>
        </div>

        {/* <div className="infoItem">
          <span className="infoLabel">
            PROFILE
          </span>

          <a href="/u/rupesh">
            mediahub.com/u/rupesh
            <span>↗</span>
          </a>
        </div> */}

      </div>

      {/* Actions */}
      <div className="profileActions">

        {/* <a
          href="/profile"
          className="editProfileBtn"
        >
          <span>Edit profile</span>
          <span>→</span>
        </a> */}

        <button className="logoutBtn" onClick={handleClick}>
          <span>Logout</span>
          <span>↗</span>
        </button>

      </div>

      {/* Footer */}
      <div className="profileFooter">
        <span>MEDIAHUB</span>
        <span>ACCOUNT</span>
      </div>

    </div>
  );
};

export default Profile;