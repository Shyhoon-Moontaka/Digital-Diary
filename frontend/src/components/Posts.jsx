import { useNavigate } from "react-router-dom";

function Posts({ posts }) {
  const navigate = useNavigate();

  return (
    <div className="flex justify-center">
      <div className="mt-12 grid grid-cols-3 gap-6 w-full max-w-5xl">
        {posts.map((post) => (
          <div
            key={post.id}
            onClick={() => navigate(`/post/${post.id}`)}
            className="p-[5vh] border border-[gray] rounded-2xl shadow-2xl shadow-[blue] hover:shadow-3xl hover:shadow-[black] flex flex-col justify-center"
          >
            <h1>Date: {new Date(post.createdAt).toLocaleDateString()}</h1>
            <h1>
              Author: {post.userFirstName} {post.userLastName}
            </h1>
            <h1 className="break-words">Title: {post.title}</h1>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Posts;
