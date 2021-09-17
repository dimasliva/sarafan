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
    export default {
        components: {
            MessagesList,
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
    }
    }
</script>

<style scoped>

</style>