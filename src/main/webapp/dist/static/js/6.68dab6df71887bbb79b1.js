webpackJsonp([6],{"9pOC":function(a,t,e){t=a.exports=e("FZ+f")(!0),t.push([a.i,"\n.index-warp h2[data-v-05a84a86] {\n  color: red;\n}\n.index-warp .title[data-v-05a84a86] {\n  text-align: left;\n  font-size: 20px;\n  display: inline-block;\n  float: left;\n}\n.index-warp .search[data-v-05a84a86] {\n  width: 300px;\n  float: left;\n  margin: 10px 0 0 40px;\n}\n.index-warp .pagination[data-v-05a84a86] {\n  text-align: right;\n  margin-top: 20px;\n}\n","",{version:3,sources:["D:/food_master/src/components/page/Index.vue"],names:[],mappings:";AACA;EACE,WAAW;CACZ;AACD;EACE,iBAAiB;EACjB,gBAAgB;EAChB,sBAAsB;EACtB,YAAY;CACb;AACD;EACE,aAAa;EACb,YAAY;EACZ,sBAAsB;CACvB;AACD;EACE,kBAAkB;EAClB,iBAAiB;CAClB",file:"Index.vue",sourcesContent:["\n.index-warp h2[data-v-05a84a86] {\n  color: red;\n}\n.index-warp .title[data-v-05a84a86] {\n  text-align: left;\n  font-size: 20px;\n  display: inline-block;\n  float: left;\n}\n.index-warp .search[data-v-05a84a86] {\n  width: 300px;\n  float: left;\n  margin: 10px 0 0 40px;\n}\n.index-warp .pagination[data-v-05a84a86] {\n  text-align: right;\n  margin-top: 20px;\n}\n"],sourceRoot:""}])},G56w:function(a,t,e){var n=e("9pOC");"string"==typeof n&&(n=[[a.i,n,""]]),n.locals&&(a.exports=n.locals);e("rjj0")("7bf3233e",n,!0)},qXVv:function(a,t,e){"use strict";function n(a){e("G56w")}Object.defineProperty(t,"__esModule",{value:!0});var l=e("uWBr"),r=new l.a,i={data:function(){return{tableData:[],cur_page:1,select_word:"",listNum:null}},beforeMount:function(){var a=this,t={api:"/product/index/orderInfo",param:{type:"0",pagesNum:a.cur_page}};r.post(t).then(function(t){a.tableData=t.data.data.order,a.listNum=t.data.data.listNum,a.changeType(a.tableData)})},methods:{handleCurrentChange:function(a){this.cur_page=a;var t=this,e={api:"/product/index/orderInfo",param:{type:"0",pagesNum:t.cur_page}};r.post(e).then(function(a){t.tableData=a.data.data.order,t.changeType(t.tableData)})},changeType:function(a){a.map(function(a,t,e){switch(a.type){case"0":e[t].typech="未支付，未发货";break;case"1":e[t].typech="已支付，未发货";break;case"2":e[t].typech="未支付，已发货";break;case"3":e[t].typech="已支付已发货"}})}},computed:{indexData:function(){var a=this;return a.tableData.filter(function(t){if(t.goodsName.indexOf(a.select_word)>-1)return t})}}},o=function(){var a=this,t=a.$createElement,e=a._self._c||t;return e("div",{staticClass:"index-warp"},[e("p",{staticClass:"title"},[a._v("未完成订单")]),a._v(" "),e("el-input",{staticClass:"search",attrs:{placeholder:"筛选产品关键词"},model:{value:a.select_word,callback:function(t){a.select_word=t},expression:"select_word"}}),a._v(" "),e("el-table",{ref:"indexTable",staticStyle:{width:"100%"},attrs:{data:a.indexData,border:""}},[e("el-table-column",{attrs:{type:"index",label:"序号",width:"50"}}),a._v(" "),e("el-table-column",{attrs:{prop:"customer",label:"客户名称",width:"150"}}),a._v(" "),e("el-table-column",{attrs:{prop:"createdate",label:"订单日期",width:"150"}}),a._v(" "),e("el-table-column",{attrs:{prop:"goodsName",label:"产品名称",width:"200"}}),a._v(" "),e("el-table-column",{attrs:{prop:"goodsQuantity",label:"数量",width:"50"}}),a._v(" "),e("el-table-column",{attrs:{prop:"goodsPrice",label:"价格",width:"150"}}),a._v(" "),e("el-table-column",{attrs:{prop:"readydate",label:"交付日期",width:"200"}}),a._v(" "),e("el-table-column",{attrs:{prop:"phone",label:"手机号",width:"150"}}),a._v(" "),e("el-table-column",{attrs:{prop:"address",label:"收货地址",width:"200"}}),a._v(" "),e("el-table-column",{attrs:{prop:"logistics",label:"物流单号",width:"150"}}),a._v(" "),e("el-table-column",{attrs:{prop:"typech",label:"状态"}})],1),a._v(" "),e("div",{staticClass:"pagination"},[e("el-pagination",{attrs:{layout:"prev, pager, next",total:a.listNum},on:{"current-change":a.handleCurrentChange}})],1)],1)},s=[],p={render:o,staticRenderFns:s},d=p,c=e("VU/8"),u=n,h=c(i,d,!1,u,"data-v-05a84a86",null);t.default=h.exports}});
//# sourceMappingURL=6.68dab6df71887bbb79b1.js.map