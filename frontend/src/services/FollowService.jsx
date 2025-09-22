import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class FollowService {
  followers(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "getFollowers", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }
  following(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "getFollowing", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  isFollowing(followingId, token) {
    return (
      token &&
      axios.get(REACT_APP_API + `follows/isFollowing/${followingId}`, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  follow(values, token) {
    return (
      token &&
      axios.post(REACT_APP_API + "follows/add", values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }
  unfollow(values, token) {
    return (
      token &&
      axios.post(REACT_APP_API + "follows/delete", values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }
}
