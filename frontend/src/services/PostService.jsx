import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class PostService {
  add(values, token) {
    return (
      token &&
      axios.post(REACT_APP_API + "posts/add", values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  getAllByUserId(userId, token) {
    return (
      token &&
      userId &&
      axios.get(REACT_APP_API + `posts/getAllByUser/${userId}`, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  getAllByMyself(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "posts/getAllByMyself", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  getAllByUserFollowing(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "posts/getAllByUserFollowing", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  getById(postId, token) {
    return (
      token &&
      axios.get(REACT_APP_API + "posts/getById/" + postId, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }
}
