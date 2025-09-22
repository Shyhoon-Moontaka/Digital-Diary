import { useNavigate } from "react-router-dom";

function NetworkCard({ users, loading, handleFollow, handleUnFollow }) {
  const navigate = useNavigate();

  return (
    <div className="max-w-4xl mx-auto px-4 py-8">
      {loading ? (
        <p className="text-center text-gray-500 text-lg">Loading users...</p>
      ) : users.length === 0 ? (
        <p className="text-center text-gray-500 text-lg">No users found.</p>
      ) : (
        <div className="grid md:grid-cols-2 gap-6">
          {users.map(([user, isFollowing]) => (
            <div
              key={user.id}
              className="border rounded-xl p-3 pb-10 shadow-sm hover:shadow-md transition duration-300 bg-white"
            >
              <div
                onClick={() => navigate(`/profile/${user.id}`)}
                className="w-12 cursor-pointer h-12 rounded-full bg-gray-500 flex items-center justify-center text-white font-bold text-lg"
              >
                {user.firstName?.charAt(0).toUpperCase()}
              </div>
              <h2 className="text-xl font-semibold text-gray-800">
                {user.firstName} {user.lastName}
              </h2>
              <p className="text-gray-500 mb-4">{user.email}</p>

              {isFollowing ? (
                <button
                  onClick={() => handleUnFollow(user.id)}
                  className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded transition"
                >
                  Unfollow
                </button>
              ) : (
                <button
                  onClick={() => handleFollow(user.id)}
                  className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded transition"
                >
                  Follow
                </button>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default NetworkCard;
