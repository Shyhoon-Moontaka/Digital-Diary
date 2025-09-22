import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class LikeService {
  add(postId, token) {
    const values = { postId };
    return (
      token &&
      axios.post(REACT_APP_API + "likes/add", values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  delete(postId, token) {
    const values = { postId };
    return (
      token &&
      axios.post(REACT_APP_API + "likes/delete", values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  isLiked(postId, token) {
    return (
      token &&
      axios.get(REACT_APP_API + `likes/isliked/${postId}`, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  getLikesByPost(postId, token) {
    return axios.get(REACT_APP_API + "likes/getallbypost/" + postId, {
      headers: {
        Authorization: "Bearer " + token,
      },
    });
  }
}
