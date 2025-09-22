import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import FollowService from "../services/FollowService";

function UserCard({
  image,
  fullName,
  followers,
  following,
  isFollowing,
  isOwner,
  userId,
  checkIsFollowing,
}) {
  const { token } = useContext(AuthContext);
  const followService = new FollowService();

  const handleFollow = async (followingId) => {
    try {
      const values = { followingId };
      const result = await followService.follow(values, token);
      checkIsFollowing();
      alert("Followed");
    } catch (error) {
      alert("Follow failed");
      console.log(error);
    }
  };

  const handleUnFollow = async (followingId) => {
    try {
      const values = { followingId };
      await followService.unfollow(values, token);
      checkIsFollowing();
      alert("Unfollowed");
    } catch (error) {
      alert("Unfollow failed");
    }
  };

  return (
    <div className="flex justify-center xl:justify-end w-full xl:fixed xl:right-12 xl:top-12 mt-4">
      <div className="max-w-[270px] w-full bg-white dark:bg-gray-800 shadow-2xl rounded-md overflow-hidden">
        <img
          className="h-32 w-full object-cover"
          src="https://images.unsplash.com/photo-1612865547334-09cb8cb455da?ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"
          alt="Cover"
        />
        <div className="flex justify-center -mt-12">
          <img
            className="w-24 h-24 rounded-full border-4 border-white object-cover"
            src={image}
            alt={fullName}
          />
        </div>

        <div className="p-6 text-center">
          <h2 className="text-2xl font-semibold">{fullName}</h2>
          <div className="flex justify-center gap-10 mt-4">
            <div>
              <p className="font-semibold">{followers}</p>
              <p className="text-sm text-gray-500">Followers</p>
            </div>
            <div>
              <p className="font-semibold">{following}</p>
              <p className="text-sm text-gray-500">Following</p>
            </div>
          </div>

          {!isOwner &&
            (isFollowing ? (
              <button
                className="mt-8 w-full bg-gray-800 text-white py-2 rounded-md hover:bg-gray-700 transition"
                onClick={() => handleUnFollow(user.id)}
              >
                Followed
              </button>
            ) : (
              <button
                className="mt-8 w-full bg-pink-600 text-white py-2 rounded-md hover:bg-pink-700 transition"
                onClick={() => handleFollow(user.id)}
              >
                Follow
              </button>
            ))}
        </div>
      </div>
    </div>
  );
}

export default UserCard;
