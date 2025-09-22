import { useContext, useEffect, useState } from "react";
import Nav from "../components/Nav";
import Posts from "../components/Posts";
import AuthContext from "../context/AuthContext";
import PostService from "../services/PostService";

function Home() {
  const { token } = useContext(AuthContext);
  const [posts, setPosts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const postService = new PostService();
      try {
        const result = await postService.getAllByUserFollowing(token);
        setPosts(result.data);
      } catch (error) {
        alert(error.message);
      }
    };

    fetchData();
  }, [token]);

  const filteredPosts = posts.filter((post) =>
    JSON.stringify(post).toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <>
      <Nav />

      <div className="flex justify-center mt-[5%]">
        <input
          type="text"
          placeholder="Search Posts..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border border-gray-500 rounded px-4 py-2 max-w-lg min-w-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
        />
      </div>

      {filteredPosts.length > 0 ? (
        <Posts posts={filteredPosts} />
      ) : (
        <div className="flex flex-col items-center justify-center h-screen">
          <h1 className="text-3xl font-semibold mb-6">No posts to show</h1>
        </div>
      )}
    </>
  );
}

export default Home;
