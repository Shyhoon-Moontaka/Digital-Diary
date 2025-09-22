import axios from "axios";
import { ADMIN_APP_API } from "../utils/ApiUrl";

export default class AdminService {
  login(values) {
    return axios.post(ADMIN_APP_API + "login", values);
  }

  register(values) {
    return axios.post(ADMIN_APP_API + "register", values);
  }

  getAllUsers(token) {
    return (
      token &&
      axios.get(ADMIN_APP_API + "users", {
        headers: { Authorization: "Bearer " + token },
      })
    );
  }

  createUser(values, token) {
    return (
      token &&
      axios.post(ADMIN_APP_API + "users", values, {
        headers: { Authorization: "Bearer " + token },
      })
    );
  }

  updateUser(id, values, token) {
    return (
      token &&
      axios.put(ADMIN_APP_API + "users/" + id, values, {
        headers: { Authorization: "Bearer " + token },
      })
    );
  }

  deleteUser(id, token) {
    return (
      token &&
      axios.delete(ADMIN_APP_API + "users/" + id, {
        headers: { Authorization: "Bearer " + token },
      })
    );
  }

  getStatistics(token) {
    return (
      token &&
      axios.get(ADMIN_APP_API + "statistics", {
        headers: { Authorization: "Bearer " + token },
      })
    );
  }
}
