import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class CommentService {
  getAllByPost(postId, token) {
    return (
      token &&
      axios.get(REACT_APP_API + "comments/getallbypost/" + postId, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  add(values, token) {
    return (
      token &&
      axios.post(REACT_APP_API + "comments/add", values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }
}
