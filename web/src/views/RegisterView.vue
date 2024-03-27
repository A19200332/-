<template>
    <MyContainer>
        <div class="row justify-content-md-center">
            <div class="col-4">
                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="status" class="form-label">身份</label>
                        <!--<input v-model="status" type="text" class="form-control" id="status" placeholder="请选择身份"> -->
                        <select class="form-select form-select-sm" aria-label=".form-select-sm example" v-model="authority"
                            id="status">
                            <!-- <option selected :value="''">专业</option> -->
                            <option value="0">用户</option>
                            <option value="1">管理员</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password1" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password1" placeholder="请输入密码">
                    </div>
                    <div class="mb-3">
                        <label for="password2" class="form-label">确认密码</label>
                        <input v-model="confirmedpassword" type="password" class="form-control" id="password2"
                            placeholder="请再次输入密码">
                    </div>
                    <div class="errormassage">{{ error_massage }}</div>
                    <button type="submit" class="btn btn-primary mybutton">注册</button>
                </form>
            </div>
        </div>

    </MyContainer>
</template>
<script>
import MyContainer from "@/components/MyContainer.vue";
import { ref } from "vue";
import { useStore } from "vuex";
import $ from 'jquery';
import router from '../router/index'
export default {
    components: {
        MyContainer,
    },
    setup() {
        let authority = ref('');
        let username = ref('');
        let password = ref('');
        let confirmedpassword = ref('');
        let error_massage = ref('');
        const store = useStore();
        const register = () => {
            $.ajax({
                url: "http://localhost:4000/user/account/register/",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmedpassword: confirmedpassword.value,
                    authority: authority.value,

                },
                success(resp) {
                    if (resp.error_massage === "success") {
                        store.dispatch("login", {
                            username: username.value,
                            password: password.value,
                            success() {
                                store.dispatch("getinfo", {
                                    success() {

                                    },
                                    error() {

                                    }
                                });
                                router.push({ name: 'home' });

                            },
                            error(resp) {
                                error_massage.value = resp.error_massage;
                            }
                        });
                    }
                    else {
                        error_massage.value = resp.error_massage;
                    }
                },
                error(resp) {
                    console.log(resp);
                }

            });
        }
        return {
            username,
            password,
            confirmedpassword,
            authority,
            error_massage,
            store,
            register,
        }

    }
}
</script>
<style scoped>
.mybutton {
    width: 100%;
}

.errormassage {
    color: red;
}
</style>