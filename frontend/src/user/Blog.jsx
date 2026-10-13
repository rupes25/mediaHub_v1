import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import "../css/userCSS/Blog.css";
import api from "../api/axios";

const Blog = () => {

  const { id } = useParams();
  const navigate = useNavigate();
  const [blog, setBlog] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState("")

  useEffect(() => {
    const fetchBlog = async () => {
      try {
        const response = await api.get(`/blogs/${id}`);
        console.log(response.data);
        setBlog(response.data.blog ?? null);
      }
      catch (err) {
        console.log(err.message);
        setError(err.response?.data?.message ?? "Unable to load this blog.");
      }
      finally {
        setIsLoading(false);
      }

    };
    fetchBlog();

  }, [id])

  if (isLoading) {
    return <main className="blogPage">Loading blog...</main>;
  }

  if (error || !blog) {
    return (
      <main className="blogPage">
        <p>{error || "Blog not found."}</p>
        <button onClick={() => navigate("/feed")}>Back to feed</button>
      </main>
    );
  }



  // const blogImages = [
  //   "https://images.unsplash.com/photo-1499750310107-5fef28a66643",
  //   "https://images.unsplash.com/photo-1516321318423-f06f85e504b3",
  //   "https://images.unsplash.com/photo-1451187580459-43490279c0fa",
  //   "https://images.unsplash.com/photo-1553877522-43269d4ea984",
  //   "https://images.unsplash.com/photo-1500534623283-312aade485b7",
  // ];

  return (
    <div className="blogPage">

      <Navbar />

      <main className="fullBlogSection">

        {/* =========================
            ARTICLE HEADER
        ========================= */}

        <header className="blogHeader">

          <div className="blogMetaTop">
            <span className="blogCategoryTag">
              {blog?.category}
            </span>

            <span className="blogMetaDot">•</span>

            <span>8 min read</span>

            <span className="blogMetaDot">•</span>

            <span>Sep 13, 2026</span>
          </div>


          <h1>
            {blog?.title}
          </h1>


          <p className="blogIntro">
            {blog?.description}
          </p>


          {/* Author */}

          <div className="blogAuthorSection">

            <div className="blogAuthorAvatar">
              {blog?.author?.charAt(0).toUpperCase()}
            </div>

            <div className="blogAuthorInfo">

              <span className="writtenBy">
                WRITTEN BY
              </span>

              <strong>
                {blog?.author}
              </strong>

              <span>
                @{blog?.author}
              </span>

            </div>

          </div>

        </header>


        {/* =========================
            IMAGE GALLERY
        ========================= */}

        <section className="blogGallerySection">

          <div className="galleryHeader">

            <div>
              <span className="galleryKicker">
                STORY GALLERY
              </span>

              <p>
                Swipe to explore the story
              </p>
            </div>

            <span className="galleryCounter">
              {blog?.images?.length ?? 0} images
            </span>

          </div>


          <div className="blogImageScroller">

            {blog?.images?.map((image, index) => (
              <div
                className="blogImageSlide"
                key={`${blog._id}-${index}`}
              >
                <img
                  src={image}
                  alt={`Blog visual ${index + 1}`}
                />

                <span className="imageNumber">
                  0{index + 1}
                </span>
              </div>
            ))}

          </div>


          <div className="galleryScrollHint">
            <span></span>
            Scroll horizontally
            <span>→</span>
          </div>

        </section>


        {/* =========================
            ARTICLE CONTENT
        ========================= */}

        <article className="articleContent">

          <div className="articleLayout">

            <aside className="articleSideInfo">

              <div className="sideInfoItem">
                <span>AUTHOR</span>
                <strong>{blog?.author}</strong>
              </div>

              <div className="sideInfoItem">
                <span>CATEGORY</span>
                <strong>{blog?.category}</strong>
              </div>

              <div className="sideInfoItem">
                <span>READING TIME</span>
                <strong>8 Minutes</strong>
              </div>

            </aside>


            <div className="articleText">

              {blog?.content?.split(/\r?\n/).map((paragraph, index) => (
                paragraph.trim() && (
                  <p className={index === 0 ? "articleLead" : ""} key={index}>
                    {paragraph}
                  </p>
                )
              ))}


              <div className="articleEnd">

                <span className="endLine"></span>

                <span>
                  End of article
                </span>

                <span className="endLine"></span>

              </div>

            </div>

          </div>

        </article>


        {/* =========================
            BACK BUTTON
        ========================= */}

        <div className="backToFeed">

          <button onClick={() => navigate("/feed")}>
            <span>←</span>
            Back to feed
          </button>

        </div>

      </main>

    </div>
  );
};

export default Blog;