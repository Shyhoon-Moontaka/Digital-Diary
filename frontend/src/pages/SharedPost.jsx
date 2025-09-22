import { useContext, useEffect, useState } from "react";
import Nav from "../components/Nav";
import AuthContext from "../context/AuthContext";
import PostService from "../services/PostService";
import { useParams } from "react-router-dom";
import PostCard from "../components/PostCard";

function SharedPost() {
  const { token } = useContext(AuthContext);
  const { postId } = useParams();
  const [post, setPost] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      const postService = new PostService();
      try {
        if (postId !== undefined) {
          const result = await postService.getById(parseInt(postId), token);
          setPost(result.data);
        }
      } catch (error) {
        console.log(error.message);
      }
    };
    fetchData();
  }, [postId]);

  return (
    <>
      <Nav />
      {post ? (
        <div className="mt-[10%]">
          <PostCard
            authorId={post.authorId}
            title={post.title}
            description={post.description}
            userName={post.userFirstName + " " + post.userLastName}
            createdAt={post.createdAt}
            postId={post.id}
          />
        </div>
      ) : (
        <div className="flex flex-col items-center justify-center h-screen">
          <h1 className="text-3xl font-semibold mb-6">No post to show</h1>
        </div>
      )}
    </>
  );
}

export default SharedPost;
