import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class NotificationService {
  getNotification(token) {
    return (
      token &&
      axios.get(REACT_APP_API + "notification/get", {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  add(message, redirectUrl, userId, token) {
    return (
      token &&
      message &&
      redirectUrl &&
      userId &&
      axios.post(REACT_APP_API + "notification/add", values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }

  delete(id, token) {
    return (
      token &&
      axios.post(REACT_APP_API + "notification/delete/" + id, values, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
    );
  }
}
