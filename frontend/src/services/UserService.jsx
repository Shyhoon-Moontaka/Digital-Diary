import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class UserService {
  getAllUser(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "getall", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  getOtherProfileData(userId, token) {
    return (
      token &&
      axios.get(REACT_APP_API + "getOtherProfile/" + userId, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  getMyProfileData(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "getMyProfile", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  isFollowing(followingId, token) {
    return (
      token &&
      axios.get(REACT_APP_API + `isfollowing?followingId=${followingId}`, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }
}
