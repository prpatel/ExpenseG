import java.text.SimpleDateFormat
class ItemSearchService {

    def  executeCriteriaQuery(params) {
        Item.createCriteria().list {
            and {
                if(params.payee) {
                    eq('payee', params.payee)
                }
                if(params.expenseType?.id != "null" && params.expenseType?.id) {
                    eq('expenseType.id', Long.parseLong(params.expenseType.id))
                }
                if(params.event?.id != "null" && params.event?.id) {
                    eq('event.id', Long.parseLong(params.event.id))
                }
                if(params.toDate_year != null && params.toDate_year != "null" && params.fromDate_year != "null" && params.toDate_year != "" && params.fromDate_year != "") {
                    println "|" + params.toDate_year + "|"
                    println params.toDate_month + "." + params.toDate_day + "." + params.toDate_year
                    SimpleDateFormat formatter = new java.text.SimpleDateFormat("MM.dd.yyyy")
                    between('createDate', formatter.parse(params.fromDate_month + "." + params.fromDate_day + "." + params.fromDate_year),
                        formatter.parse(params.toDate_month + "." + params.toDate_day + "." + params.toDate_year))
                }
            }
            maxResults((params.max))
            firstResult((params.offset))

        }
    }

    def checkEmpty = {
        params.expenseType.id != "null" && params.expenseType.id
    }
}