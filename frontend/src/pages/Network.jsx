import { useContext, useEffect, useState } from "react";
import AuthContext from "../context/AuthContext";
import UserService from "../services/UserService";
import FollowService from "../services/FollowService";
import NetworkCard from "../components/NetworkCard";
import Nav from "../components/Nav";
import NetworkContext from "../context/NetworkContext";

function Network() {
  const {
    fetchUsers,
    fetchFollowing,
    fetchFollowers,
    handleFollow,
    handleUnFollow,
    setSearchTerm,
    filteredUsers,
    loading,
    searchTerm,
    users,
  } = useContext(NetworkContext);

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div className="max-w-4xl mx-auto px-4 py-8">
      <Nav />

      <div className="flex justify-center mt-[5%]">
        <input
          type="text"
          placeholder="Search People..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border border-gray-500 rounded px-4 py-2 max-w-lg min-w-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
        />
      </div>

      <div className="flex justify-center gap-4 my-8">
        <button
          onClick={fetchUsers}
          className="bg-blue-500 hover:bg-blue-600 text-white font-medium px-4 py-2 rounded transition"
        >
          People
        </button>
        <button
          onClick={fetchFollowing}
          className="bg-purple-500 hover:bg-purple-600 text-white font-medium px-4 py-2 rounded transition"
        >
          Following
        </button>
        <button
          onClick={fetchFollowers}
          className="bg-green-500 hover:bg-green-600 text-white font-medium px-4 py-2 rounded transition"
        >
          Followers
        </button>
      </div>
      <div>
        <h1 className="font-bold">Users: {users?.length}</h1>
      </div>
      <NetworkCard
        users={filteredUsers}
        loading={loading}
        handleFollow={handleFollow}
        handleUnFollow={handleUnFollow}
      />
    </div>
  );
}

export default Network;
