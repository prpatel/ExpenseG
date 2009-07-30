class ApprovalController {

  // @TODO add admin security constraint

  def itemSearchService

  def index = { redirect(action: search, params: params) }

  def search = {


    if (request.method == 'POST') {

      if (!params.max) params.max = 10
      if (!params.offset) params.offset = 0
      render(view: 'list',
              model: [itemList: itemSearchService.executeCriteriaQuery(params), params: params])
    } else {
      render(view: 'search')
    }

  }

  def approveItems = {
    def approvedIds = params.approvedIds
    if (approvedIds) {
      approvedIds = approvedIds.class.isArray() ? approvedIds : [approvedIds]
    }
    def allIds = params.allIds
    if (allIds) {
      allIds = allIds.class.isArray() ? allIds : [allIds]
    }
    if (approvedIds != null) approvedIds = approvedIds.toList() else approvedIds = []
    allIds.each {
      def item = Item.findById(it)
      item.approved = approvedIds.contains(it)
      item.save()
    }

    // @TODO add flash message to let user know N number of records were updated
    allIds = allIds.collect {it.toLong()}

    // both work, 2nd is new in Grails 1.1
    //def itemList = Item.findAll("from Item item where item.id in (:allIds)", ["allIds": allIds])
    def itemList = Item.findAllByIdInList(allIds)

    flash.message = "${itemList.size()} items updated"
    render(view: 'results', model: [itemList: itemList])

  }
}
