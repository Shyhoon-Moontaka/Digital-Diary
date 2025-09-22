import React, { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Nav from "../components/Nav";
import Posts from "../components/Posts";
import UserCard from "../components/UserCard";
import AuthContext from "../context/AuthContext";
import PostService from "../services/PostService";
import UserService from "../services/UserService";
import UserImageService from "../services/UserImageService";

function ProfileView() {
  const { token } = useContext(AuthContext);
  const { userId } = useParams();
  const [imageUrl, setImageUrl] = useState(null);
  const [userData, setUserData] = useState(null);
  const [posts, setPosts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");

  async function fetchData() {
    const userResponse = await new UserService().getOtherProfileData(
      parseInt(userId),
      token
    );
    setUserData(userResponse.data);
    const userImageResponse =
      await new UserImageService().downloadOtherProfileImage(userId, token);
    const url = URL.createObjectURL(userImageResponse.data);
    setImageUrl(url);
    const result = await new PostService().getAllByUserId(userId, token);
    setPosts(result.data);
  }

  useEffect(() => {
    fetchData();
  }, [userId]);

  const filteredPosts = posts.filter((post) =>
    JSON.stringify(post).toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="block">
      <Nav />
      <label className="flex w-[15%] h-[15%] mx-auto mt-[5%]">
        <img
          src={imageUrl || "../src/svgs/profile.png"}
          alt="User Profile"
          className="border-3 rounded-full"
        />
      </label>

      <h1 className="m-[2%] text-4xl font-bold text-center text-gray-800">
        {userData?.firstName + " " + userData?.lastName}
      </h1>

      <h1 className="m-[1%] text-2xl text-center">{userData?.email}</h1>

      <div className="flex justify-center mt-[5%]">
        <input
          type="text"
          placeholder="Search Posts..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border border-gray-500 rounded px-4 py-2 max-w-lg min-w-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
        />
      </div>

      {filteredPosts.length > 0 ? (
        <Posts posts={filteredPosts} />
      ) : (
        <div className="flex flex-col items-center justify-center h-screen">
          <h1 className="text-3xl font-semibold mb-6">No posts to show</h1>
        </div>
      )}
    </div>
  );
}

export default ProfileView;
