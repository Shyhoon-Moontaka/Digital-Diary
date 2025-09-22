import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { BrowserRouter } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext.jsx";
import { NetworkProvider } from "./context/NetworkContext.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <NetworkProvider>
          <App />
        </NetworkProvider>
      </AuthProvider>
    </BrowserRouter>
  </StrictMode>
);
