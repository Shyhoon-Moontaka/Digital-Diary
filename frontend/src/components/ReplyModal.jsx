import React, { useState } from "react";

function ReplyModal({ commentId, token }) {
  const [isOpen, setIsOpen] = useState(false);
  const [reply, setReply] = useState("");
  const [replies, setReplies] = useState([
    { id: 1, commentId: 101, user: "Alice", description: "Nice point!" },
    { id: 2, commentId: 101, user: "Bob", description: "I agree with you." },
  ]);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!reply.trim()) return;

    const newReply = {
      id: Date.now(),
      commentId,
      user: "DemoUser",
      description: reply,
    };

    setReplies([...replies, newReply]);
    setReply("");
  };

  return (
    <div className="mt-2">
      <button
        onClick={() => setIsOpen(true)}
        className="text-bold text-[red] underline hover:text-[black] ml-2"
      >
        Show Replies
      </button>

      {isOpen && (
        <div className="fixed inset-80 bg-[black] flex justify-center items-center z-50">
          <div className="bg-white rounded-lg shadow-xl p-6 w-96 relative">
            <div
              onClick={() => setIsOpen(false)}
              className="absolute top-3 right-3 hover:text-red-500 cursor-pointer text-lg"
            >
              ✕
            </div>

            <h2 className="text-lg font-semibold mb-4">Replies</h2>

            <div className="space-y-3 max-h-60 overflow-y-auto mb-4">
              {replies
                .filter((r) => r.commentId === commentId)
                .map((r) => (
                  <div key={r.id} className="flex items-start space-x-2">
                    {/* Avatar */}
                    <div className="w-7 h-7 rounded-full bg-gray-300 flex-shrink-0"></div>

                    <div className="bg-gray-100 px-3 py-2 rounded-2xl text-sm max-w-sm">
                      <span className="font-semibold">{r.user}</span>{" "}
                      <span>{r.description}</span>
                    </div>
                  </div>
                ))}
            </div>

            <form onSubmit={handleSubmit} className="flex flex-col space-y-2">
              <textarea
                value={reply}
                onChange={(e) => setReply(e.target.value)}
                placeholder="Write your reply..."
                className="px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400 resize-none text-sm"
                rows={2}
              />
              <button
                type="submit"
                className="self-end bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 text-sm font-medium"
              >
                Reply
              </button>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default ReplyModal;
