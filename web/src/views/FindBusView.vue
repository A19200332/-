<template>
    <MyContainer>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>
                        #
                    </th>
                    <th>
                        线路号
                    </th>
                    <th>
                        起点
                    </th>
                    <th>
                        终点
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>

                    </td>
                    <td>
                        <input v-model="busnumber" type="text" class="form-control" id="busnumber">
                    </td>
                    <td>
                        <input v-model="originstation" type="text" class="form-control" id="originstation">
                    </td>
                    <td>
                        <input v-model="destination" type="text" class="form-control" id="destination">
                    </td>
                    <td>
                        <button type="button" class="btn btn-secondary" @click="getlist">查询</button>
                        <!-- <button type="button" class="btn btn-primary has-left" data-bs-toggle="modal"
                            data-bs-target="#addlabel">添加</button> -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#addlabel">添加</button>

                        <!--添加Modal-->
                        <div class="modal fade" id="addlabel" tabindex="-1" aria-labelledby="addlabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addlabel">添加公交线路</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <form @submit.prevent="add">
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="exampleInputPassword1" class="form-label">站点1</label>
                                                <input v-model="station1" type="text" class="form-control"
                                                    id="exampleInputPassword1">
                                            </div>
                                            <div class="mb-3">
                                                <label for="exampleInputPassword2" class="form-label">站点2</label>
                                                <input v-model="station2" type="text" class="form-control"
                                                    id="exampleInputPassword2">
                                            </div>
                                            <div class="mb-3">
                                                <label for="exampleInputPassword3" class="form-label">站点3</label>
                                                <input v-model="station3" type="text" class="form-control"
                                                    id="exampleInputPassword3">
                                            </div>
                                            <div class="mb-3">
                                                <label for="exampleInputPassword4" class="form-label">站点4</label>
                                                <input v-model="station4" type="text" class="form-control"
                                                    id="exampleInputPassword4">
                                            </div>
                                            <div class="mb-3">
                                                <label for="exampleInputPassword5" class="form-label">站点5</label>
                                                <input v-model="station5" type="text" class="form-control"
                                                    id="exampleInputPassword5">
                                            </div>


                                        </div>
                                        <div class="modal-footer">
                                            <div class="errormassage">
                                                {{ error_massage }}
                                            </div>
                                            <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">取消</button>
                                            <button type="submit" class="btn btn-primary">添加</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!--添加Modal-->
                    </td>

                </tr>
                <tr v-for="(bus, index) in buses" :key="bus.id" :id="index">
                    <td>
                        {{ index + 1 }}
                    </td>
                    <td>
                        {{ bus.id }}
                    </td>
                    <td v-if="bus.station1 == originstation">
                        {{ bus.station1 }}
                    </td>
                    <td v-else-if="bus.station2 == originstation">
                        {{ bus.station2 }}
                    </td>
                    <td v-else-if="bus.station3 == originstation">
                        {{ bus.station3 }}
                    </td>
                    <td v-else-if="bus.station4 == originstation">
                        {{ bus.station4 }}
                    </td>
                    <td v-else-if="bus.station5 == originstation">
                        {{ bus.station5 }}
                    </td>
                    <td v-else>

                    </td>
                    <td v-if="bus.station1 == destination">
                        {{ bus.station1 }}
                    </td>
                    <td v-else-if="bus.station2 == destination">
                        {{ bus.station2 }}
                    </td>
                    <td v-else-if="bus.station3 == destination">
                        {{ bus.station3 }}
                    </td>
                    <td v-else-if="bus.station4 == destination">
                        {{ bus.station4 }}
                    </td>
                    <td v-else-if="bus.station5 == destination">
                        {{ bus.station5 }}
                    </td>
                    <td v-else>

                    </td>
                    <td>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updatelabel"
                            @click="update_prepare(bus)">修改</button>
                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deletelabel"
                            @click="update_delete_bus_id(bus.id)">删除</button>
                        <!--deleteModal-->
                        <div class="modal fade" id="deletelabel" tabindex="-1" aria-labelledby="deletelabel"
                            aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="deletelabel">
                                            <i class="bi bi-exclamation-octagon">
                                                <img src="../assets/exclamation-octagon.svg">
                                            </i>
                                            warning
                                        </h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        确认删除此条记录？
                                    </div>
                                    <div class="modal-footer">
                                        <div class="errormassage">
                                            {{ error_massage }}
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" @click="deletebus">确认删除</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--deleteModal-->
                        <!--updateModal-->
                        <div class="modal fade" id="updatelabel" tabindex="-1" aria-labelledby="updatelabel"
                            aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="updatelabel">修改公交线路</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <form @submit.prevent="update(bus)">
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="InputPassword1" class="form-label">站点1</label>
                                                <input v-model="update_station1" type="text" class="form-control"
                                                    id="InputPassword1">
                                            </div>
                                            <div class="mb-3">
                                                <label for="InputPassword2" class="form-label">站点2</label>
                                                <input v-model="update_station2" type="text" class="form-control"
                                                    id="InputPassword2">
                                            </div>
                                            <div class="mb-3">
                                                <label for="InputPassword3" class="form-label">站点3</label>
                                                <input v-model="update_station3" type="text" class="form-control"
                                                    id="InputPassword3">
                                            </div>
                                            <div class="mb-3">
                                                <label for="InputPassword4" class="form-label">站点4</label>
                                                <input v-model="update_station4" type="text" class="form-control"
                                                    id="InputPassword4">
                                            </div>
                                            <div class="mb-3">
                                                <label for="InputPassword5" class="form-label">站点5</label>
                                                <input v-model="update_station5" type="text" class="form-control"
                                                    id="InputPassword5">
                                            </div>


                                        </div>
                                        <div class="modal-footer">
                                            <div class="errormassage">
                                                {{ error_massage }}
                                            </div>
                                            <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">取消</button>
                                            <button type="submit" class="btn btn-primary">修改</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!--updateModal-->
                    </td>

                </tr>

            </tbody>

        </table>

    </MyContainer>
</template>
<script>
import MyContainer from '@/components/MyContainer.vue';
import $ from 'jquery';
import { ref } from 'vue';
import { useStore } from 'vuex';
import { Modal } from 'bootstrap/dist/js/bootstrap';
export default {
    me: "FindBusView",
    components: {
        MyContainer,
    },
    setup() {
        let station1 = ref('');
        let station2 = ref('');
        let station3 = ref('');
        let station4 = ref('');
        let station5 = ref('');
        let busnumber = ref('');
        let originstation = ref('');
        let destination = ref('');
        let error_massage = ref('');
        let delete_bus_id = ref('');
        let buses = ref([]);
        let update_index = ref('');
        let update_station1 = ref('');
        let update_station2 = ref('');
        let update_station3 = ref('');
        let update_station4 = ref('');
        let update_station5 = ref('');
        let list = [];
        const store = useStore();
        const update_delete_bus_id = (temp) => {
            delete_bus_id.value = temp;
        }
        const add = () => {
            $.ajax({
                url: "http://localhost:4000/bus/select/add/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    stations: "#" + station1.value + "_#" + station2.value + "_#" + station3.value + "_#" + station4.value + "_#" + station5.value,
                    authority: store.state.user.authority,
                },
                success(resp) {
                    if (resp.error_massage === "success") {
                        // getlist();
                        console.log(resp);
                        Modal.getInstance("#addlabel").hide();
                    } else {
                        error_massage.value = resp.error_massage;
                        console.log("失败");
                    }
                },
            });
        }
        const getlist = () => {
            $.ajax({
                url: "http://localhost:4000/bus/select/getlist/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    id: busnumber.value,
                    origin_station: originstation.value,
                    destination_station: destination.value,
                },
                success(resp) {
                    buses.value = [];
                    buses.value = resp;
                    console.log(resp);
                },
                error(resp) {
                    console.log(resp)
                }
            })
            list = [];
            for (let i = 0; i < length(list); i++) {
                list.add(0);
            }
        }
        const deletebus = () => {
            $.ajax({
                url: "http://localhost:4000/bus/select/delete/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    id: delete_bus_id.value,
                    authority: store.state.user.authority,
                },
                success(resp) {
                    if (resp.error_massage == "success") {
                        getlist();
                        console.log(resp);
                        Modal.getInstance("#deletelabel").hide();
                    } else {
                        error_massage.value = resp.error_massage;
                        console.log("失败");
                    }
                },
                error(resp) {
                    console.log(resp)
                }
            })
        }
        const update_prepare = (temp) => {
            update_index.value = temp.id;
            update_station1.value = temp.station1;
            update_station2.value = temp.station2;
            update_station3.value = temp.station3;
            update_station4.value = temp.station4;
            update_station5.value = temp.station5;
            console.log(update_index.value);
        }
        const update = (temp) => {
            $.ajax({
                url: "http://localhost:4000/bus/select/update/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    id: temp.id,
                    stations: "#" + update_station1.value + "_#" + update_station2.value + "_#" + update_station3.value + "_#" + update_station4.value + "_#" + update_station5.value,
                    authority: store.state.user.authority,
                },
                success(resp) {
                    if (resp.error_massage == "success") {
                        console.log(resp);
                        Modal.getInstance("#updatelabel").hide();
                        getlist();
                    } else {
                        error_massage.value = resp.error_massage;
                        console.log("失败");
                    }
                },
                error(resp) {
                    console.log(resp)
                }
            })
        }
        return {
            add,
            getlist,
            update_delete_bus_id,
            deletebus,
            update_prepare,
            update,
            error_massage,
            station1,
            station2,
            station3,
            station4,
            station5,
            busnumber,
            originstation,
            destination,
            delete_bus_id,
            buses,
            update_index,
            update_station1,
            update_station2,
            update_station3,
            update_station4,
            update_station5,
            list,

        }
    }
}
</script>
<style scoped>
.has-left {
    margin-left: 5px;
}

.errormassage {
    color: red;
}

.floatt {
    position: absolute;
}
</style>