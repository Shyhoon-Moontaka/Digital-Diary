import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class UserImageService {
  downloadOtherProfileImage(userId, token) {
    return (
      token &&
      userId &&
      axios.get(REACT_APP_API + "userimages/downloadOther/" + userId, {
        headers: {
          Authorization: "Bearer " + token,
        },
        responseType: "blob",
      })
    );
  }

  downloadMyProfileImage(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "userimages/downloadMine", {
        headers: {
          Authorization: "Bearer " + token,
        },
        responseType: "blob",
      })
    );
  }

  upload(data, token) {
    return (
      token &&
      axios.post(REACT_APP_API + "userimages/upload", data, {
        headers: {
          "content-type": "multipart/form-data",
          Authorization: "Bearer " + token,
        },
      })
    );
  }
}
