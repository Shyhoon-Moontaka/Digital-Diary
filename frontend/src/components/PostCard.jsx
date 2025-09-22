import { useContext, useEffect, useState } from "react";
import { BiLike, BiChat, BiShare } from "react-icons/bi";
import { Link } from "react-router-dom";
import AuthContext from "../context/AuthContext";
import LikeService from "../services/LikeService";
import CommentModal from "./CommentModal";
import LikeModal from "./LikeModal";
import PostImageService from "../services/PostImageService";

function PostCard({
  userName,
  authorId,
  title,
  description,
  createdAt,
  postId,
}) {
  const likeService = new LikeService();
  const { token } = useContext(AuthContext);
  const [isLiked, setIsLiked] = useState(false);
  const [likes, setLikes] = useState([]);
  const [imageUrl, setImageUrl] = useState(null);

  const fetchImage = async () => {
    const postImageService = new PostImageService();
    try {
      const postImageResponse = await postImageService.download(postId, token);
      const url = URL.createObjectURL(postImageResponse.data);
      setImageUrl(url);
    } catch (error) {}
  };

  const fetchLikes = async () => {
    const likeService = new LikeService();
    try {
      const result = await likeService.getLikesByPost(postId, token);
      const likeStatus = await likeService.isLiked(postId, token);
      setLikes(result.data);
      setIsLiked(likeStatus.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchLikes();
    fetchImage();
  }, [postId, token]);

  const handleLike = async () => {
    try {
      await likeService.add(postId, token);
      fetchLikes();
    } catch (error) {
      console.log(error);
    }
  };

  const handleUnlike = async () => {
    try {
      await likeService.delete(postId, token);
      fetchLikes();
    } catch (error) {
      console.log(error);
    }
  };

  const handleShare = () => {
    try {
      navigator.clipboard
        .writeText(`${window.location.origin}/post/${postId}`)
        .then(() => {
          alert(
            "Post has been copied to clipboard.Now you can share this url to anyone."
          );
        });
    } catch (error) {
      alert(error);
    }
  };

  return (
    <div className="min-w-lg max-w-lg mx-auto bg-white shadow-lg rounded-xl overflow-hidden mb-6 border">
      <Link to={`/profile/${authorId}`}>
        <div className="flex items-center gap-4 p-4">
          <div className="w-12 h-12 rounded-full bg-gray-500 flex items-center justify-center text-white font-bold text-lg">
            {userName?.charAt(0).toUpperCase()}
          </div>
          <div>
            <h2 className="text-sm font-semibold">{userName}</h2>
          </div>
        </div>
      </Link>

      {createdAt && (
        <div className="px-4 pb-4">
          <p className="text-gray-700">
            Date: {new Date(createdAt).toLocaleDateString()}
          </p>
        </div>
      )}

      <div className="px-4 pb-4">
        <p className="text-gray-700">Title: {title}</p>
      </div>

      <div className="px-4 pb-4">
        <p className="text-gray-700">Description: {description}</p>
      </div>

      <div>
        <img
          src={imageUrl}
          className={imageUrl ? "flex rounded-[20%] w-full p-[5%]" : "hidden"}
        />
      </div>

      <div className="flex justify-between items-center px-4 py-3 gap-10">
        <div className="flex flex-1 flex-row gap-2">
          {isLiked ? (
            <button
              onClick={handleUnlike}
              className="bg-violet-500 text-white p-2 rounded-lg hover:bg-blue-600 transition"
            >
              <BiLike />
            </button>
          ) : (
            <button
              onClick={handleLike}
              className="text-gray-700 border border-gray-600 p-2 rounded-lg hover:bg-gray-100 transition"
            >
              <BiLike />
            </button>
          )}
          <LikeModal likes={likes} isLiked={isLiked} />
        </div>
        <div className="flex-1">
          <CommentModal postId={postId} />
        </div>
        <button
          onClick={handleShare}
          className="flex-1 cursor-pointer flex items-center justify-center gap-2 text-gray-700 border border-gray-300 py-2 rounded-lg hover:bg-gray-100 transition"
        >
          <BiShare />
          Share
        </button>
      </div>
    </div>
  );
}

export default PostCard;
