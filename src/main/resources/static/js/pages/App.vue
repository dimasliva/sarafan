<template>
    <div>
        <div v-if="!profile">Необходибо авторизоваться:
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>{{profile.name}}<a href="/logout">&nbsp;Выйти</a></div>
            <messages-list :messages="messages"/>
        </div>
    </div>
</template>

<script>
    import MessagesList from "components/messages/MessageList.vue";
    import {addHandler} from "util/ws";
    import {getIndex} from "util/collections";

    export default {
        components: {
            MessagesList,
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id);
                if(index > -1) {
                    this.messages.splice(index, 1, data)
                } else {
                    this.messages.push(data)
                }
            })
        }
    }
</script>

<style scoped>

</style>