import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class PostImageService {
  download(postId, token) {
    return (
      token &&
      axios.get(REACT_APP_API + "postImages/download/" + postId, {
        headers: {
          Authorization: "Bearer " + token,
        },
        responseType: "blob",
      })
    );
  }
  upload(postId, data, token) {
    return (
      token &&
      axios.post(REACT_APP_API + "postImages/upload/" + postId, data, {
        headers: {
          "content-type": "multipart/form-data",
          Authorization: "Bearer " + token,
        },
      })
    );
  }
}
