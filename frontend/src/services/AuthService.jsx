import axios from "axios";
import { REACT_APP_API } from "../utils/ApiUrl";

export default class AuthService {
  register(values) {
    return axios.post(REACT_APP_API + "register", values);
  }
  login(values) {
    return axios.post(REACT_APP_API + "login", values);
  }
}
