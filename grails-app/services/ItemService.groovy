//import javax.jws.*;


//@WebService(targetNamespace="http://com.mypatelspace")
class ItemService {

    boolean transactional = true

    def persistItem(Item item) {
        return validateAndSave(item)
    }
//    @WebMethod
    def String save(
        String desc,
        Date createDate,
        String payee,
        Float amount,
        Long expenseType,
        Long event,
        Long user,
        Long paymentType,
        boolean submitted,
        boolean reimbursed) {
        Item item = new Item(desc: desc,
            createDate: createDate,
            payee: payee,
            amount: amount,
            expenseType: ExpenseType.findById(expenseType),
            event: ExpenseEvent.findById(event),
            user: User.findById(user),
            // FIX THE ENUM BELOW
            paymentType: PaymentType.CASH,
            submitted: submitted,
            reimbursed: reimbursed
        )
        return validateAndSave(item)

    }

//    @WebMethod
    def String get (Long id) {
        //doSomeHeavyStuff()
        return Item.findById(id)
    }


    def validateAndSave = { object ->
        def validate = object.validate()
        def message = object.errors.allErrors.inject('') {str, error ->
            str + "Field error in object ${error.objectName} on field ${error.field}: rejected value [${error.rejectedValue}];"
        }
        if (validate && object.save()) {
            return "SUCCESS"
        } else {
            return message
        }
    }


    def doSomeHeavyStuff() {
        def String someBigString = new String();
        def builder = NodeBuilder.newInstance()
        def xmlns = new groovy.xml.NamespaceBuilder(builder)

        def xsd = xmlns.namespace('http://www.w3.org/2001/XMLSchema', 'xsd')

        def root = xsd.schema(xmlns:['foo':'http://someOtherNamespace']) {
            annotation {
                documentation("Purchase order schema for Example.com.")
                //documentation(xmlns=[xml.lang:'en']) ["Purchase order schema for Example.com."]
            }
            element(name:'purchaseOrder', type:'PurchaseOrderType')
            element(name:'comment', type:'xsd:string')
            complexType(name:'PurchaseOrderType') {
                sequence {
                    element(name:'shipTo', type:'USAddress')
                    element(name:'billTo', type:'USAddress')
                    element(minOccurs:'0', ref:'comment')
                    element(name:'items', type:'Items')
                }
                attribute(name:'orderDate', type:'xsd:date')
            }
            complexType(name:'USAddress') {
                sequence {
                    element(name:'name', type:'xsd:string')
                    element(name:'street', type:'xsd:string')
                    element(name:'city', type:'xsd:string')
                    element(name:'state', type:'xsd:string')
                    element(name:'zip', type:'xsd:decimal')
                }
                attribute(fixed:'US', name:'country', type:'xsd:NMTOKEN')
            }
            complexType(name:'Items') {
                sequence {
                    element(maxOccurs:'unbounded', minOccurs:'0', name:'item') {
                        complexType {
                            sequence {
                                element(name:'productName', type:'xsd:string')
                                element(name:'quantity') {
                                    simpleType {
                                        restriction(base:'xsd:positiveInteger') {
                                            maxExclusive(value:'100')
                                        }
                                    }
                                }
                                element(name:'USPrice', type:'xsd:decimal')
                                element(minOccurs:'0', ref:'comment')
                                element(minOccurs:'0', name:'shipDate', type:'xsd:date')
                            }
                            attribute(name:'partNum', type:'SKU', use:'required')
                        }
                    }
                }
            }
            /* Stock Keeping Unit, a code for identifying products */
            simpleType(name:'SKU') {
                restriction(base:'xsd:string') {
                    pattern(value:'\\d{3}-[A-Z]{2}')
                }
            }
        }

        someBigString = builder
    }

}
