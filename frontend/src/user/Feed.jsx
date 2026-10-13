import { useEffect, useState } from "react";
import BlogCard from "../components/BlogCard";
import "../css/userCSS/Feed.css";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";

const Feed = () => {

  const navigate = useNavigate();

  const [blogs, setBlogs] = useState([])

  useEffect(() => {
    const fetchBlogs = async () => {
      try {
        const response = await api.get("/blogs");
        console.log(response.data);
        setBlogs(response.data.blogs ?? [])
      }
      catch (err) {
        console.log(err.message);
        alert("Something went wrong.")
      }

    }
    fetchBlogs();

  }, [])



  const handleViewBlog = (id) => {
    console.log("Opening blog:", id);

    // Later:
    navigate(`/blogs/${id}`);
  };

  return (
    <main className="feedPage">

      {/* Background decoration */}
      <div className="feedGlow feedGlowOne"></div>
      <div className="feedGlow feedGlowTwo"></div>

      {/* =========================
          HERO
      ========================= */}

      <section className="feedHero">

        <div className="feedHeroContent">

          <div className="feedEyebrow">
            <span className="eyebrowDot"></span>
            MEDIAHUB · COMMUNITY STORIES
          </div>

          <h1>
            Discover ideas.
            <br />
            <span>Read something meaningful.</span>
          </h1>

          <p>
            Explore stories, experiences and ideas shared by creators
            across the MediaHUB community.
          </p>

        </div>

        <div className="feedHeroStats">

          <div className="feedStat">
            <strong>1.2K+</strong>
            <span>Stories</span>
          </div>

          <div className="feedStatDivider"></div>

          <div className="feedStat">
            <strong>420+</strong>
            <span>Creators</span>
          </div>

        </div>

      </section>


      {/* =========================
          FEATURED / LATEST
      ========================= */}

      <section className="blogFeedSection">

        <div className="sectionHeader">

          <div>
            <span className="sectionKicker">
              EXPLORE
            </span>

            <h2>
              Latest from the community
            </h2>

            <p>
              Fresh perspectives and stories worth your time.
            </p>
          </div>

          <button className="exploreBtn">
            Explore all
            <span>→</span>
          </button>

        </div>


        {/* Blog horizontal feed */}

        <div className="blogScrollWrapper">

          <div className="blogSection">

            {blogs.map((blog) => (
              <BlogCard
                key={blog._id}
                image={blog.images?.[0]}
                title={blog.title}
                description={blog.description}
                author={blog.author}
                category={blog.category}
                onClick={() => handleViewBlog(blog._id)}
              />
            ))}

          </div>

        </div>


        {/* Scroll hint */}

        <div className="feedScrollHint">
          <span className="scrollLine"></span>
          <span>Scroll to discover more</span>
          <span className="scrollArrow">→</span>
        </div>

      </section>


      {/* =========================
          TOPICS
      ========================= */}

      <section className="topicsSection">

        <div className="topicsHeading">
          <span className="sectionKicker">
            FIND YOUR INTEREST
          </span>

          <h2>
            Explore by topic
          </h2>
        </div>

        <div className="topicList">

          <button>Technology</button>
          <button>Development</button>
          <button>Cybersecurity</button>
          <button>Travel</button>
          <button>Personal</button>

        </div>

      </section>

    </main>
  );
};

export default Feed;