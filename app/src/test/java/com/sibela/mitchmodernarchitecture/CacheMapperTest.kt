package com.sibela.mitchmodernarchitecture

import com.sibela.mitchmodernarchitecture.model.Blog
import com.sibela.mitchmodernarchitecture.room.BlogCacheEntity
import com.sibela.mitchmodernarchitecture.room.CacheMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CacheMapperTest {

    private val cacheMapper = CacheMapper()

    @Test
    fun `testing mapFromEntity when object has empty fields`() {
        val title = ""
        val body = ""
        val image = ""
        val category = ""

        val blogCacheEntity = BlogCacheEntity(0, title, body, image, category)
        val blog = cacheMapper.mapFromEntity(blogCacheEntity)

        assertTrue(blog.title.isBlank())
        assertTrue(blog.body.isBlank())
        assertTrue(blog.image.isBlank())
        assertTrue(blog.category.isBlank())
    }

    @Test
    fun `testing mapFromEntity when object has filled fields`() {
        val id = 0
        val title = "Vancouver PNE 2019"
        val body = "Here is Jess and I at the Vancouver PNE. We ate a lot of food."
        val image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.png"
        val category = "fun"

        val blogCacheEntity = BlogCacheEntity(id, title, body, image, category)
        val blog = cacheMapper.mapFromEntity(blogCacheEntity)

        assertEquals("", blog.id, id)
        assertEquals("", blog.title, title)
        assertEquals("", blog.body, body)
        assertEquals("", blog.image, image)
        assertEquals("", blog.category, category)
    }

    @Test
    fun `testing mapToEntity when object has empty fields`() {
        val id = 42
        val title = ""
        val body = ""
        val image = ""
        val category = ""

        val blog = Blog(id, title, body, image, category)
        val blogCacheEntity = cacheMapper.mapToEntity(blog)

        assertTrue("blogCacheEntity.title should be empty", blogCacheEntity.title.isBlank())
        assertTrue("blogCacheEntity.body should be empty", blogCacheEntity.body.isBlank())
        assertTrue("blogCacheEntity.image should be empty", blogCacheEntity.image.isBlank())
        assertTrue("blogCacheEntity.category should be empty", blogCacheEntity.category.isBlank())
    }

    @Test
    fun `testing mapToEntity when object has filled fields`() {
        val id = 0
        val title = "Vancouver PNE 2019"
        val body = "Here is Jess and I at the Vancouver PNE. We ate a lot of food."
        val image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.png"
        val category = "fun"

        val blog = Blog(id, title, body, image, category)
        val blogCacheEntity = cacheMapper.mapToEntity(blog)

        assertEquals(
            "blogCacheEntity.id is ${blogCacheEntity.id} but it should be $id",
            blogCacheEntity.id,
            id
        )

        assertEquals(
            "blogCacheEntity.title is ${blogCacheEntity.title} but it should be $title",
            blogCacheEntity.title,
            title
        )

        assertEquals(
            "blogCacheEntity.body is ${blogCacheEntity.body} but it should be $body",
            blogCacheEntity.body,
            body
        )

        assertEquals(
            "blogCacheEntity.image is ${blogCacheEntity.image} but it should be $image",
            blogCacheEntity.image,
            image
        )

        assertEquals(
            "blogCacheEntity.category is ${blogCacheEntity.category} but it should be $category",
            blogCacheEntity.category,
            category
        )
    }

    @Test
    fun `testing mapFromEntityList`() {
        val firstId = 0
        val firstTitle = "Vancouver PNE 2019"
        val firstBody = "Here is Jess and I at the Vancouver PNE. We ate a lot of food."
        val firstImage = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.png"
        val firstCategory = "fun"
        val firstBlogCacheEntity =
            BlogCacheEntity(firstId, firstTitle, firstBody, firstImage, firstCategory)

        val secondId = 1
        val secondTitle = "Ready for a Walk"
        val secondBody = "Here I am at the park with my dogs Kiba and Maizy. Maizy is the smaller one and Kiba is the larger one."
        val secondImage = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image2.png"
        val secondCategory = "dogs"
        val secondBlogCacheEntity =
            BlogCacheEntity(secondId, secondTitle, secondBody, secondImage, secondCategory)

        val entities = listOf(firstBlogCacheEntity, secondBlogCacheEntity)
        val blogs = cacheMapper.mapFromEntityList(entities)

        assertEquals(
            "Blogs should have two elements but it has ${blogs.size}", blogs.size, 2
        )


        val firstBlog = blogs[0]

        assertEquals(
            "firstBlog.id is ${firstBlog.id} but it should be $firstId",
            firstBlog.id,
            firstId
        )

        assertEquals(
            "firstBlog.title is ${firstBlog.title} but it should be $firstTitle",
            firstBlog.title,
            firstTitle
        )

        assertEquals(
            "firstBlog.body is ${firstBlog.body} but it should be $firstBody",
            firstBlog.body,
            firstBody
        )

        assertEquals(
            "firstBlog.image is ${firstBlog.image} but it should be $firstImage",
            firstBlog.image,
            firstImage
        )

        assertEquals(
            "firstBlog.category is ${firstBlog.category} but it should be $firstCategory",
            firstBlog.category,
            firstCategory
        )


        val secondBlog = blogs[1]

        assertEquals(
            "secondBlog.id is ${secondBlog.id} but it should be $secondId",
            secondBlog.id,
            secondId
        )

        assertEquals(
            "secondBlog.title is ${secondBlog.title} but it should be $secondTitle",
            secondBlog.title,
            secondTitle
        )

        assertEquals(
            "secondBlog.body is ${secondBlog.body} but it should be $secondBody",
            secondBlog.body,
            secondBody
        )

        assertEquals(
            "secondBlog.image is ${secondBlog.image} but it should be $secondImage",
            secondBlog.image,
            secondImage
        )

        assertEquals(
            "secondBlog.category is ${secondBlog.category} but it should be $secondCategory",
            secondBlog.category,
            secondCategory
        )
    }
}