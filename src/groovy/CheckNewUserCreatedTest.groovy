import functionaltestplugin.*
class CheckNewUserCreatedTest extends functionaltestplugin.FunctionalTestCase {
    void testSearch() {
        get('http://twitter.com')
        click "Search"

        form("searchForm") {
            lastName = "patel"
            click "Search"
        }
        assertContentContains "Burks Item"

    }
}