import { createContext, useEffect, useState } from "react";
import UserService from "../services/UserService";
import FollowService from "../services/FollowService";
import AuthContext from "./AuthContext";
import { useContext } from "react";

const NetworkContext = createContext();

export const NetworkProvider = ({ children }) => {
  const { token } = useContext(AuthContext);
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [loading, setLoading] = useState(true);

  const userService = new UserService();
  const followService = new FollowService();

  const fetchIsFollowing = async (followingId) => {
    try {
      const res = await followService.isFollowing(parseInt(followingId), token);
      return res.data;
    } catch (err) {
      return false;
    }
  };

  const fetchUsers = async () => {
    setLoading(true);
    try {
      const res = await userService.getAllUser(token);
      const filteredUsers = await Promise.all(
        res.data.map(async (user) => {
          const isFollowing = await fetchIsFollowing(user.id);
          return [user, isFollowing];
        })
      );
      filteredUsers.sort((a, b) => {
        return b[1] - a[1];
      });
      setUsers(filteredUsers);
    } catch (err) {
      alert(err);
    } finally {
      setLoading(false);
    }
  };

  const fetchFollowing = async () => {
    setLoading(true);
    try {
      const res = await followService.following(token);
      const filteredUsers = await Promise.all(
        res.data.map(async (user) => {
          const isFollowing = await fetchIsFollowing(user.id);
          return [user, isFollowing];
        })
      );
      filteredUsers.sort((a, b) => {
        return b[1] - a[1];
      });
      setUsers(filteredUsers);
    } catch (err) {
      alert(err);
    } finally {
      setLoading(false);
    }
  };

  const fetchFollowers = async () => {
    setLoading(true);
    try {
      const res = await followService.followers(token);
      const filteredUsers = await Promise.all(
        res.data.map(async (user) => {
          const isFollowing = await fetchIsFollowing(user.id);
          return [user, isFollowing];
        })
      );
      filteredUsers.sort((a, b) => {
        return b[1] - a[1];
      });
      setUsers(filteredUsers);
    } catch (err) {
      alert(err);
    } finally {
      setLoading(false);
    }
  };

  const handleFollow = async (followingId) => {
    try {
      const payload = { followingId };
      await followService.follow(payload, token);
      await fetchUsers();
    } catch (err) {
      console.error("Follow failed", err);
    }
  };

  const handleUnFollow = async (followingId) => {
    try {
      const payload = {
        followingId: parseInt(followingId),
      };
      await followService.unfollow(payload, token);
      await fetchUsers();
    } catch (err) {
      console.error("Unfollow failed", err);
    }
  };

  const filteredUsers = users.filter((user) =>
    JSON.stringify(user).toLowerCase().includes(searchTerm.toLowerCase())
  );

  const value = {
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
  };

  return (
    <NetworkContext.Provider value={value}>{children}</NetworkContext.Provider>
  );
};

export default NetworkContext;
