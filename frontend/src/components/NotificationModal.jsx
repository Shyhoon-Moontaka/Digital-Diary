import { useContext, useEffect, useState } from "react";
import { Bell } from "lucide-react"; // or react-icons
import NotificationService from "../services/NotificationService";
import AuthContext from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

function NotificationModal() {
  const { token } = useContext(AuthContext);
  const [showNotifications, setShowNotifications] = useState(false);
  const [notifications, setNotifications] = useState([]);
  const navigate = useNavigate();

  const fetchNotification = async () => {
    const notificationService = new NotificationService();
    try {
      const notification = (await notificationService.getNotification(token))
        .data;
      setNotifications(notification);
    } catch (error) {
      alert(error);
    }
  };

  useEffect(() => {
    fetchNotification();
  }, [token]);

  return (
    <div className="fixed top-5 left-50 z-50">
      <button
        className="relative p-2 rounded-full hover:bg-gray-200 transition"
        onClick={() => setShowNotifications(!showNotifications)}
      >
        <Bell size={26} />
        {notifications.length > 0 && (
          <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs px-1 rounded-full">
            {notifications.length}
          </span>
        )}
      </button>

      {showNotifications && (
        <div className="fixed top-20 left-0 w-80 bg-[black] shadow-xl rounded-xl border border-gray-200">
          <div className="flex justify-between items-center p-3 border-b">
            <h2 className="font-semibold text-[white]">Notifications</h2>
            <button
              className="text-sm text-blue-500"
              onClick={() => setShowNotifications(false)}
            >
              Close
            </button>
          </div>
          <div className="max-h-60 overflow-y-auto">
            {notifications.length > 0 ? (
              notifications.map((n) => (
                <div
                  key={n.id}
                  className="p-3 hover:bg-[gray] cursor-pointer border-b text-[white]"
                  onClick={() => navigate("/" + n.redirectUrl)}
                >
                  {n.message}
                </div>
              ))
            ) : (
              <div className="p-3 text-gray-500 text-center">
                No notifications
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default NotificationModal;
