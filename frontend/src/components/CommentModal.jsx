import React, { useContext, useEffect, useState } from "react";
import CommentService from "../services/CommentService";
import AuthContext from "../context/AuthContext";
import { Link, useNavigate } from "react-router-dom";
import ReplyModal from "./ReplyModal";

function CommentModal({ postId }) {
  const { token } = useContext(AuthContext);
  const [comments, setComments] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
  const [description, setDescription] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchComments = async () => {
      const commentService = new CommentService();
      try {
        const result = await commentService.getAllByPost(postId, token);
        setComments(result.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchComments();
  }, [postId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const commentService = new CommentService();
    const payload = {
      postId,
      description,
    };
    try {
      await commentService.add(payload, token);
      setDescription("");

      const result = await commentService.getAllByPost(postId, token);
      setComments(result.data);
    } catch (error) {
      alert(error);
    }
  };

  return (
    <>
      <button
        onClick={() => setIsOpen(true)}
        className="text-blue-600 hover:text-blue-800 font-medium cursor-pointer"
      >
        Comment ({comments.length})
      </button>

      {isOpen && (
        <div className="fixed inset-0 bg-black flex justify-center items-center z-50">
          <div className="bg-white  min-w-lg rounded-lg shadow-lg p-6 relative">
            <div
              onClick={() => setIsOpen(false)}
              className="absolute top-2 right-2 hover:text-[red] cursor-pointer"
            >
              Close
            </div>

            <h2 className="text-xl font-semibold mb-4">Comments</h2>

            <form
              onSubmit={handleSubmit}
              className="flex items-center space-x-2 mb-6"
            >
              <input
                type="text"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                placeholder="Share a comment"
                className="flex-1 px-4 py-2 border rounded-md focus:outline-none focus:bg-green-100 focus:ring-2 focus:ring-blue-400"
              />
              <button
                type="submit"
                className="cursor-pointer bg-violet-500 text-white px-4 py-2 rounded-md hover:bg-violet-600 text-sm"
              >
                Share
              </button>
            </form>

            <div className="space-y-4 max-h-80 overflow-y-auto">
              {comments.map((comment) => (
                <div
                  key={comment.id}
                  className="block border rounded-md p-3 pb-7 hover:bg-gray-50"
                >
                  <div
                    onClick={() => navigate(`/profile/${comment.userId}`)}
                    className="w-12 cursor-pointer h-12 rounded-full bg-gray-500 flex items-center justify-center text-white font-bold text-lg"
                  >
                    {comment.userFirstName?.charAt(0).toUpperCase()}
                  </div>

                  <h3 className="text-md font-semibold">
                    {comment.userFirstName + " " + comment.userLastName}
                  </h3>

                  <p className="text-gray-700">{comment.description}</p>

                  <ReplyModal commentId={comment.id} token={token} />
                </div>
              ))}
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default CommentModal;
