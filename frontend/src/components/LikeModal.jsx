import React, { useState } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../context/AuthContext";
import LikeService from "../services/LikeService";
import { BiLike } from "react-icons/bi";

function LikeModal({ likes, isLiked }) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <>
      <button
        onClick={() => setIsOpen(true)}
        className="text-blue-600 hover:text-blue-800 font-medium cursor-pointer"
      >
        Like ({likes.length})
      </button>

      {isOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-white w-full max-w-md rounded-lg shadow-lg p-6 relative">
            <div
              onClick={() => setIsOpen(false)}
              className="absolute top-2 right-2 hover:text-[red] cursor-pointer"
            >
              Close
            </div>

            <h2 className="text-xl font-semibold mb-4">
              <div
                className={`cursor-pointer w-[6%] border-[2px] ${
                  isLiked ? "bg-[blue] text-[white]" : "bg-[white] text-[gray]"
                }`}
              >
                <BiLike />
              </div>
              People who liked
            </h2>

            <div className="space-y-4 max-h-80 overflow-y-auto">
              {likes.length === 0 ? (
                <p className="text-gray-600">No likes yet.</p>
              ) : (
                likes.map((like) => (
                  <Link
                    to={`/profile/${like.userId}`}
                    key={like.id}
                    className="flex flex-row border justify-center items-center rounded-lg p-3 hover:bg-red-100 gap-[2%]"
                  >
                    <div className="w-12 cursor-pointer h-12 rounded-full bg-gray-500 flex items-center justify-center text-white font-bold text-lg">
                      {like.firstName?.charAt(0).toUpperCase()}
                    </div>

                    <h3 className="text-md font-medium">
                      {like.firstName + " " + like.lastName}
                    </h3>
                  </Link>
                ))
              )}
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default LikeModal;
