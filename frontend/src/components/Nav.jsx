import { useContext } from "react";
import { BiAward, BiHome, BiLogOut, BiUserPlus } from "react-icons/bi";
import { CgProfile } from "react-icons/cg";
import AuthContext from "../context/AuthContext";
import { Link } from "react-router-dom";
import NotificationModal from "./NotificationModal";

function Nav() {
  const { logout } = useContext(AuthContext);

  return (
    <nav className="fixed top-0 left-0 w-full bg-white shadow-md z-50">
      <div className="max-w-7xl mx-auto px-4 py-3 flex items-center justify-between">
        <div className="flex flex-col text-xl font-bold text-gray-800 flex space-x-2">
          <div className="flex flex-row text-green-700">
            <BiAward />
            Digital Diary
          </div>
          <sub>Powered by JUST CSE</sub>
          <NotificationModal />
        </div>

        <div className="flex space-x-6 items-center">
          <Link
            to="/home"
            className="text-gray-700 hover:text-blue-600 font-medium flex items-center space-x-1"
          >
            <BiHome />
            <span>Home</span>
          </Link>

          <Link
            to="/network"
            className="text-gray-700 hover:text-blue-600 font-medium flex items-center space-x-1"
          >
            <BiUserPlus />
            <span>Network</span>
          </Link>

          <Link
            to={`/profile/edit`}
            className="text-gray-700 hover:text-blue-600 font-medium flex items-center space-x-1"
          >
            <CgProfile />
            <span>Profile</span>
          </Link>

          <Link
            to="/addPost"
            className="text-gray-700 hover:text-blue-600 font-medium"
          >
            Share Post
          </Link>

          <button
            onClick={logout}
            className="text-gray-700 hover:text-blue-600 font-medium"
          >
            <span>Logout</span>
            <BiLogOut />
          </button>
        </div>
      </div>
    </nav>
  );
}

export default Nav;
