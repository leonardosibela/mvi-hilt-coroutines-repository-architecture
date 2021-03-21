package com.sibela.mitchmodernarchitecture.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class BlogDaoTest {

    private lateinit var database: BlogDatabase
    private lateinit var dao: BlogDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BlogDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.blogDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertBlog() = runBlocking {
        val blog = BlogCacheEntity(1, "Title", "body", "image", "category")
        dao.insert(blog)
        val blogs = dao.getAll()
        Assert.assertEquals(1, blogs.size)

        val fetchedBlog = blogs[0]
        Assert.assertEquals(blog.id, fetchedBlog.id)
        Assert.assertEquals(blog.title, fetchedBlog.title)
        Assert.assertEquals(blog.body, fetchedBlog.body)
        Assert.assertEquals(blog.image, fetchedBlog.image)
        Assert.assertEquals(blog.category, fetchedBlog.category)
    }
}