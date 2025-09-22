import React, { useContext, useState } from "react";
import PostService from "../services/PostService";
import AuthContext from "../context/AuthContext";
import Nav from "../components/Nav";
import PostImageService from "../services/PostImageService";

function AddPost() {
  const { userId, token } = useContext(AuthContext);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [image, setImage] = useState(null);
  const [preview, setPreview] = useState("src/svgs/undraw_no_data_re_kwbl.svg");
  const [loading, setLoading] = useState(false);
  const postService = new PostService();
  const postImageService = new PostImageService();

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    setImage(file);
    if (file) {
      setPreview(URL.createObjectURL(file));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    try {
      const values = {
        title,
        description,
        userId,
      };

      const postId = (await postService.add(values, token)).data;

      if (image && postId) {
        const formData = new FormData();
        formData.append("image", image);
        await postImageService.upload(postId, formData, token);
        setPreview("src/svgs/undraw_no_data_re_kwbl.svg");
        setImage(null);
      }

      alert("Post shared successfully!");
      setTitle("");
      setDescription("");
    } catch (error) {
      alert(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <Nav />
      <div className="flex justify-center items-center mt-[3%]">
        <form
          onSubmit={handleSubmit}
          className="bg-white rounded-lg shadow-lg max-w-md w-full p-6 relative"
          onClick={(e) => e.stopPropagation()}
        >
          <h2 className="text-xl font-semibold mb-4">Share a post</h2>

          <label className="block mb-2 font-medium" htmlFor="title">
            Title
          </label>
          <textarea
            id="title"
            placeholder="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="w-full border border-gray-300 rounded px-3 py-2 mb-4 focus:outline-none focus:ring-2 focus:ring-violet-500 resize-none"
            rows={1}
            required
          />

          <label className="block mb-2 font-medium" htmlFor="description">
            Description
          </label>
          <textarea
            id="description"
            placeholder="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="w-full border border-gray-300 rounded px-3 py-2 mb-4 focus:outline-none focus:ring-2 focus:ring-violet-500 resize-none"
            rows={4}
            required
          />

          <label className="font-medium text-center" htmlFor="image">
            Upload Image
          </label>
          <label className="flex justify-center cursor-pointer my-auto mx-auto mt-[5%]">
            <img
              className="border-3 rounded-3xl"
              src={preview}
              alt="No Photo Chosen."
            />
            <input
              type="file"
              onChange={handleImageChange}
              className="hidden"
            />
          </label>

          <div className="mt-6 flex justify-end space-x-3">
            <button
              type="submit"
              disabled={loading}
              className="bg-violet-500 hover:bg-violet-600 text-white font-semibold py-2 px-4 rounded disabled:opacity-50"
            >
              {loading ? "Sharing..." : "Share"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddPost;
